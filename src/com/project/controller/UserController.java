package com.project.controller;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Menu;
import com.project.model.Menu_Ordered;
import com.project.model.Menu_temp;
import com.project.model.Order_details;
import com.project.model.User;
import com.project.service.CookQueueService;
import com.project.service.MenuTempService;
import com.project.service.UserService;
import com.project.service.menuService;

@Controller
public class UserController {
	ArrayList<String> s= new ArrayList<String>();
	ArrayList<String> u= new ArrayList<String>();
	//User Login
		@RequestMapping(value="/userlogin", method=RequestMethod.POST)
		public ModelAndView showMenu(HttpServletRequest request, HttpServletResponse response)
		{
			//System.out.println("inside show menu:"+email+" , "+password);
			String email=request.getParameter("email");
			String password=request.getParameter("password");
		
			
			User user=new User();
			UserService userService=new UserService();
			menuService mService=new menuService();
			
			user.setEmail_id(email);
			user.setPassword(password);
			
			int success=userService.userLogin(user);
			if(success!=0)
			{
			
			List<Menu> menus= mService.showAllMenu();
			
			List<Menu> drink=new ArrayList<Menu>();
			List<Menu> appetizer=new ArrayList<Menu>();
			List<Menu> main_course=new ArrayList<Menu>();
			List<Menu> dessert=new ArrayList<Menu>();
			
			for(Menu m:menus)
			{
				if(m.getMenu_status().equalsIgnoreCase("TRUE"))
				{
					if(m.getCategory().equalsIgnoreCase("DRINK"))
						drink.add(m);
					else if(m.getCategory().equalsIgnoreCase("APPETIZER"))
						appetizer.add(m);
					else if(m.getCategory().equalsIgnoreCase("MAIN COURSE"))
						main_course.add(m);
					else
						dessert.add(m);
				}
			}
			
			HttpSession session=request.getSession();
			System.out.println("session:"+session.toString());
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			System.out.println("session:"+session.getAttribute("email")+" , "+ session.getAttribute("password"));
			
			ModelAndView mv=new ModelAndView();	
			mv.addObject("drinks", drink);
			mv.addObject("appetizers", appetizer);
			mv.addObject("main_courses", main_course);
			mv.addObject("desserts", dessert);
			mv.addObject("s",session);
			mv.setViewName("menu");	
			return mv;
			}
			else
			{
				ModelAndView mv=new ModelAndView();
				mv.addObject("msg","Wrong credentials or User not present");
				mv.setViewName("loginUser");
				 return mv;
			}
				
		}

	//User Logout
	
		@RequestMapping(value="/logout",method=RequestMethod.POST )
		public ModelAndView lout(HttpServletRequest request,HttpServletResponse response)
		{
			HttpSession s=request.getSession();
			System.out.println("before invalidating :"+s.getAttribute("email"));
			s.invalidate();
			System.out.println("after invalidating :");
			return new ModelAndView("index");
		}
		
		
	//Render SignUp page
	@RequestMapping(value="/renderSignup", method=RequestMethod.GET)
	public String showSignUp()
	{
		System.out.println("inside render signup");
		//return new ModelAndView("signup.jsp");
		return "signupUser";
	}


	//Render Login page

		@RequestMapping(value="/renderLogin", method=RequestMethod.GET)
		public String showLogin()
		{
			System.out.println("inside render login user");
			//return new ModelAndView("signup.jsp");
			return "loginUser";
		}
	
	//User SignUp
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String addNewUser(HttpServletRequest request , HttpServletResponse response)
	{
		String fname=request.getParameter("first_name");
		String lname=request.getParameter("last_name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String pno=request.getParameter("phone_no");
		//Integer vcode=Integer.parseInt(request.getParameter("verification_code"));
		
		HttpSession session=request.getSession();
		session.setAttribute("first_name", fname);
		session.setAttribute("last_name", lname);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		session.setAttribute("phone_no", pno);
	
		UserService userService=new UserService();
		
		
		System.out.println("email:"+email+" password:"+password);
		//userService.addNewUser(user);
		
		//send email logic
		Menu_Ordered menu_order1= new Menu_Ordered();
		int veri_code=userService.sendmail(email,"VERIFY",0,menu_order1,"");
		System.out.println("vcode:"+veri_code);
		session.setAttribute("verification", veri_code);	
		return "verificationCode";
	}
	
	
	
	//for checking verification code
	@RequestMapping(value="/signup2",method=RequestMethod.POST)
	public String sendEmail(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("inside check verification code");
		Integer vcode=Integer.parseInt(request.getParameter("verification_code"));
		HttpSession s=request.getSession();
		System.out.println("from text box:"+vcode);
		if(vcode.equals(s.getAttribute("verification")))
		{
			User user=new User();
			UserService userService=new UserService();
			
			System.out.println("insert into db");
			user.setFirst_name((s.getAttribute("first_name")).toString());
			user.setLast_name((s.getAttribute("last_name")).toString());
			user.setEmail_id((s.getAttribute("email")).toString());
			user.setPassword((s.getAttribute("password")).toString());
			user.setPhone_number((s.getAttribute("phone_no")).toString());
			user.setVerification_code(vcode);
			user.setEnabled(true);
			User u=userService.getUser(user);
			if(u==null)
			{
				userService.addNewUser(user);
				return "index";
			}
			else
			{
				System.out.println("user already exists..!!");
			}
		}
		else
		{
			System.out.println("verification code does not match");
			
		}
		return "error";
	}
	
	//Taking order from user
	@RequestMapping(value="/receive_order",method=RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request,HttpServletResponse response, ModelMap model)
	{
	
		//ModelAndView mv=new ModelAndView();
				String array=request.getParameter("array");
				if(array.length()==0)
				{
					menuService mService=new menuService();
					List<Menu> menus= mService.showAllMenu();
					
					List<Menu> drink=new ArrayList<Menu>();
					List<Menu> appetizer=new ArrayList<Menu>();
					List<Menu> main_course=new ArrayList<Menu>();
					List<Menu> dessert=new ArrayList<Menu>();
					
					for(Menu m:menus)
					{
						if(m.getMenu_status().equalsIgnoreCase("TRUE"))
						{
							if(m.getCategory().equalsIgnoreCase("DRINK"))
								drink.add(m);
							else if(m.getCategory().equalsIgnoreCase("APPETIZER"))
								appetizer.add(m);
							else if(m.getCategory().equalsIgnoreCase("MAIN COURSE"))
								main_course.add(m);
							else
								dessert.add(m);
						}
					}
					
					HttpSession session=request.getSession();
					System.out.println("session:"+session.toString());
					
					System.out.println("session:"+session.getAttribute("email")+" , "+ session.getAttribute("password"));
					
					ModelAndView mv=new ModelAndView();	
					mv.addObject("drinks", drink);
					mv.addObject("appetizers", appetizer);
					mv.addObject("main_courses", main_course);
					mv.addObject("desserts", dessert);
					mv.addObject("s",session);
					mv.setViewName("menu");	
					return mv;
				}
				System.out.println("items is:"+array.toString());
				
				HashMap<String, Integer> order=new HashMap<String,Integer>();
				
				String[] items=array.split(",");
				String[] item_quantity;
				for(String s: items)
				{
					System.out.println(s);
					item_quantity=s.split("-");
					order.put(item_quantity[1], Integer.parseInt(item_quantity[0]));
				}
	
		String itemSet=new String();
		String qtySet=new String();
		String priceSet=new String();
		
		float price=0;
		int prepTime=0;
		int reload_menu=0;
	
		for(Entry<String, Integer> e : order.entrySet())
		{
			System.out.println("e:"+e.getKey()+","+e.getValue());
			itemSet+=","+e.getKey();
			qtySet+=","+e.getValue();
			Menu menu=menuService.getMenu(e.getKey());
			
			{
				
				priceSet+=","+menu.getUnitPrice();
				price+=Float.parseFloat(menu.getUnitPrice())*e.getValue();
				prepTime+=menu.getPreparation_time()*e.getValue();
			}
		}
		HttpSession session=request.getSession();
		session.setAttribute("item", itemSet);
		session.setAttribute("quantity", qtySet);
		session.setAttribute("price", priceSet);
		session.setAttribute("prepTime", prepTime);
		session.setAttribute("totalPrice",price );
		
			System.out.println(prepTime+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			Date time=new Date();
				
			System.out.println(dateFormat.format(date)+" "+timeFormat.format(time)+""+price +" "+ itemSet+" "+qtySet);
			CookQueueService cqs=new CookQueueService();
			s=cqs.check(dateFormat.format(date),timeFormat.format(time),prepTime);
			session.setAttribute("cook", s.get(1));
			System.out.println("back from earliest");
			System.out.println(s);
			if(("06:00:00".compareTo(s.get(0))>0) || (s.get(0).compareTo("21:00:00")>0))
			{
				System.out.println("not proper time");
				//model.addAttribute("early",s.get(0));
				model.addAttribute("msg","We wont be able to prepare you order now as we are open from 6am to 9pm. Please order later or enter a pick up time above. Sorry for the inconvinience.");
				return new ModelAndView("confirmOrder");
			}
			model.addAttribute("early",s.get(0));
			session.setAttribute("startTime", timeFormat.format(time));
			session.setAttribute("endTime", s.get(0));
			model.addAttribute("date",dateFormat.format(date));
			session.setAttribute("cookDate", dateFormat.format(date));
			return new ModelAndView("confirmOrder");
		
		/*else
		{
			return new ModelAndView("menu");
		}*/
		//return new ModelAndView("placeOrders");
	}
	
	//on confirming order
	@RequestMapping(value="/confirmOrder.html",method=RequestMethod.POST)

	public ModelAndView sendConfirmEmail(HttpServletRequest request, HttpServletResponse response, ModelMap model1) throws ParseException

	{
		//UserService us=new UserService();
		ModelAndView model=new ModelAndView();
		CookQueueService cqs=new CookQueueService();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		String pickup_date=request.getParameter("pickup_date");
		String pickup_time=request.getParameter("pickup_time");
		Date sysTime=new Date();
		String sysT=timeFormat.format(sysTime);
		sysTime=timeFormat.parse(sysT);
		System.out.println("date SYSTEM:"+sysTime);
		
		Date d=null;
		Date t=null;
		Date early=null;
		try {
			 d=dateFormat.parse(pickup_date);
			 early=timeFormat.parse(s.get(0));
			 t=timeFormat.parse(pickup_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date todayDate= new Date();
		System.out.println("user date"+d);
		System.out.println("todays date"+todayDate);
		if(todayDate.compareTo(d)>0)
		{
		if(early.compareTo(t)>0)
		{
			System.out.println("not proper time");
			model1.addAttribute("early",s.get(0));
			model1.addAttribute("msg","Please enter a valid time");
			return new ModelAndView("confirmOrder");
		}
	}
		//cqs.check(d);
		
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		session.setAttribute("pickDate", dateFormat.format(d));
		session.setAttribute("pickTime", timeFormat.format(t));
		System.out.println("email to:"+email);
		
		UserService user= new UserService();

		
		
		String [] menuItem=session.getAttribute("item").toString().split(",");
		String [] menuQty=session.getAttribute("quantity").toString().split(",");
		String [] menuPrice=session.getAttribute("price").toString().split(",");
		int total_price=0;
		for(int i1=1;i1<menuQty.length;i1++)
		{
			total_price=total_price+Integer.parseInt(menuQty[i1]);
		}
		
		List <Menu> frontList=new ArrayList<Menu>();
	//	Menu frontM=new Menu();
		System.out.println("menu");
	
		for(int i=1;i<menuItem.length;i++)
		{	Menu frontM=new Menu();
			frontM.setItem_name(menuItem[i].toString());
			frontM.setQuantity(menuQty[i].toString());
			frontM.setUnitPrice((menuPrice[i]).toString());
			frontList.add(frontM);
			System.out.println("asasa"+frontM.getItem_name());
			System.out.println(frontM.getQuantity());
			System.out.println(frontM.getUnitPrice());
		}
		
		for(Menu m: frontList)
		{
			System.out.println("list:"+m);
		}

		model.addObject("pickDate", session.getAttribute("pickDate").toString());
		model.addObject("pickTime", session.getAttribute("pickTime").toString());
		model.addObject("orderList",frontList);
		model.setViewName("showOrder");
		//anuja
		
List<Menu_Ordered> mo=new ArrayList<Menu_Ordered>();
		
HashMap<String, Integer> menu_order=new HashMap<String, Integer>();

for(int i1=1;i1<menuItem.length;i1++)
{
	//Menu_Ordered m1=new Menu_Ordered();
	menu_order.put(menuItem[i1].toString(), Integer.parseInt(menuQty[i1].toString()));
	//m1.setMenu_name(menuItem[i1].toString());
	//m1.setQuantity(Integer.parseInt(menuQty[i1].toString()));
	//usr.addOrderMenu(m1);
	
}


for(Entry<String,Integer> r:  menu_order.entrySet())
{
	System.out.println("r:"+r);
	
}
		
		
		
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat form= new SimpleDateFormat("HH:mm");
		
		Order_details od1=new Order_details();
	
		od1.setUserId(session.getAttribute("email").toString());
		
		od1.setPickDate(session.getAttribute("pickDate").toString());
		od1.setPickTime(form.parse(session.getAttribute("pickTime").toString()));
		od1.setStatus("placed");
		
		od1.setPrice(session.getAttribute("totalPrice").toString());
		od1.setCook_id(Integer.parseInt(session.getAttribute("cook").toString()));
		od1.setEnd_time(form.parse(session.getAttribute("endTime").toString()));
		od1.setStart_time(form.parse(session.getAttribute("startTime").toString()));
		od1.setOrderTime(sysT);
		user.addOrder(od1);
		int order_id=user.findRecentOrderId();
		System.out.println("Order_id is:"+order_id);
		session.setAttribute("order_id", order_id);
		
		//now insert into menu_ordered table
		System.out.println("1111111111111");
		Menu_Ordered mo1=new Menu_Ordered();
		mo1.setOrder_id(order_id);
		mo1.setMenu_order(menu_order);
		System.out.println("1111111111111");
		user.addMenuOrdered(mo1);
		System.out.println("1111111111111");
		///for checking the menu_ordered table
		ArrayList<Menu_Ordered> ordered=user.getAllMenuOrdered();
		System.out.println("Menu_Ordered List");
		for(Menu_Ordered md:ordered)
		{
			System.out.println("Menu Ordered:"+md);
		}
		//anuja
	//add into order and cook table the order
		CookQueueService cq= new CookQueueService();
		cq.insertOrder(session.getAttribute("cook").toString(),session.getAttribute("startTime").toString(),session.getAttribute("endTime").toString(),session.getAttribute("cookDate").toString());
		//user.addOrder(session.getAttribute("email").toString(),session.getAttribute("item").toString(),session.getAttribute("price").toString(),session.getAttribute("quantity").toString(),session.getAttribute("pickDate").toString(),session.getAttribute("pickTime").toString(),session.getAttribute("startTime").toString(),session.getAttribute("endTime").toString(),session.getAttribute("cook").toString() );
		//send confirmation email
				UserService us=new UserService();
				ArrayList<Menu_Ordered> menu_order1=us.getAllMenuOrdered();
				Menu_Ordered send=new Menu_Ordered();
				for(Menu_Ordered m1:menu_order1)
				{
					System.out.println("menu_orders:"+m1.getMenu_order());
					for(Entry<String, Integer> e:m1.getMenu_order().entrySet())
					{
						System.out.println("Menu:"+e.getKey()+" Count:"+e.getValue());
						if(m1.getOrder_id()==order_id)
						{
							send=m1;
							break;
						}	
					}
				}	
		us.sendmail(email, "CONFIRM",order_id,send,session.getAttribute("totalPrice").toString());
		
		model.addObject("total_price",session.getAttribute("totalPrice").toString());
		return  model;
	
		
	}
	
	//for cancelling order
	@RequestMapping(value="/cancelOrder.html",method=RequestMethod.POST)
	public ModelAndView userCancelOrder(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		ModelAndView model=new ModelAndView();
		String email=session.getAttribute("email").toString();
		int orderId=Integer.parseInt(session.getAttribute("order_id").toString());
		UserService us=new UserService();
		us.cancelOrder(email,orderId);
		model.setViewName("menu");
		return model;
		
	}
	
	//check user input time
	
	@RequestMapping(value="/userTime.html",method=RequestMethod.POST)
	public ModelAndView checkUserTime(HttpServletRequest request,HttpServletResponse response,ModelMap model1) throws ParseException
	{	
		HttpSession session=request.getSession();
		ModelAndView model=new ModelAndView();
		CookQueueService cqs=new CookQueueService();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		String pickup_date=request.getParameter("pickup_date");
		String pickup_time=request.getParameter("pickup_time");
		
	
		Date sysTime=new Date();
		String sysT=timeFormat.format(sysTime);
		sysTime=timeFormat.parse(sysT);
		System.out.println("SYSTEM"+sysTime);
		Date d=null;
		Date t=null;
		Date early=null;
		try {
			 d=dateFormat.parse(pickup_date);
			 early=timeFormat.parse(s.get(0));
			 t=timeFormat.parse(pickup_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date todayDate= new Date();
		System.out.println("user date"+d);
		System.out.println("todays date"+todayDate);
		if(todayDate.compareTo(d)>0)
		{
		if(early.compareTo(t)>0)
		{
			System.out.println("not proper time");
			model1.addAttribute("early",s.get(0));
			model1.addAttribute("msg","Please enter a valid time");
			return new ModelAndView("confirmOrder");
		}
	}
		 Calendar cal = Calendar.getInstance();
		  cal.setTime(t);
		  cal.add(Calendar.HOUR, -1);
		  System.out.println("plzzzzz"+timeFormat.format(cal.getTime()));
		CookQueueService userQueue=new CookQueueService();
		u=userQueue.checkUserTime(dateFormat.format(d),timeFormat.format(cal.getTime()),Integer.parseInt(session.getAttribute("prepTime").toString()));
		//////
		if(u==null)
		{
			System.out.println("not proper time");
			//model.addAttribute("early",s.get(0));
			model.addObject("msg","We wont be able to prepare you order the requested time slot is not available. Please try selecting some other time");
			model.setViewName("confirmOrder");
			return model;
		}
		////////
		System.out.println(u.get(0)+"user check"+u.get(1)+"second"+u.get(2));
		session.setAttribute("cook", u.get(1));
		
		
		session.setAttribute("startTime", u.get(2));
		session.setAttribute("endTime", u.get(0));
	
		session.setAttribute("cookDate", dateFormat.format(d));
		//default
		
		String email=session.getAttribute("email").toString();
		session.setAttribute("pickDate", dateFormat.format(d));
		session.setAttribute("pickTime", timeFormat.format(t));
		System.out.println("email to:"+email);
		
		UserService user= new UserService();

		
		
		String [] menuItem=session.getAttribute("item").toString().split(",");
		String [] menuQty=session.getAttribute("quantity").toString().split(",");
		String [] menuPrice=session.getAttribute("price").toString().split(",");
		
		
		List <Menu> frontList=new ArrayList<Menu>();
	//	Menu frontM=new Menu();
		System.out.println("menu");
	
		for(int i=1;i<menuItem.length;i++)
		{	Menu frontM=new Menu();
			frontM.setItem_name(menuItem[i].toString());
			frontM.setQuantity(menuQty[i].toString());
			frontM.setUnitPrice((menuPrice[i]).toString());
			frontList.add(frontM);
			System.out.println("asasa"+frontM.getItem_name());
			System.out.println(frontM.getQuantity());
			System.out.println(frontM.getUnitPrice());
		}
		
		for(Menu m: frontList)
		{
			System.out.println("list:"+m);
		}

		model.addObject("pickDate", session.getAttribute("pickDate").toString());
		model.addObject("pickTime", session.getAttribute("pickTime").toString());
		model.addObject("orderList",frontList);
		model.setViewName("showOrder");
		//anuja
		

HashMap<String, Integer> menu_order=new HashMap<String, Integer>();
		
		for(int i1=1;i1<menuItem.length;i1++)
		{
			//Menu_Ordered m1=new Menu_Ordered();
			menu_order.put(menuItem[i1].toString(), Integer.parseInt(menuQty[i1].toString()));
			//m1.setMenu_name(menuItem[i1].toString());
			//m1.setQuantity(Integer.parseInt(menuQty[i1].toString()));
			//usr.addOrderMenu(m1);
			
		}
		
		
		for(Entry<String,Integer> r:  menu_order.entrySet())
		{
			System.out.println("r:"+r);
			
		}
		
		
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat form= new SimpleDateFormat("HH:mm");
		
		Order_details od1=new Order_details();
		
		od1.setUserId(session.getAttribute("email").toString());
		
		od1.setPickDate(session.getAttribute("pickDate").toString());
		od1.setPickTime(form.parse(session.getAttribute("pickTime").toString()));
		od1.setStatus("placed");
		
		od1.setPrice(session.getAttribute("totalPrice").toString());
		od1.setCook_id(Integer.parseInt(session.getAttribute("cook").toString()));
		od1.setEnd_time(form.parse(session.getAttribute("endTime").toString()));
		od1.setStart_time(form.parse(session.getAttribute("startTime").toString()));
		od1.setOrderTime(sysT);
		user.addOrder(od1);
		int order_id=user.findRecentOrderId();
		System.out.println("Order_id is:"+order_id);
		session.setAttribute("order_id", order_id);
		//now insert into menu_ordered table
		System.out.println("1111111111111");
		Menu_Ordered mo1=new Menu_Ordered();
		mo1.setOrder_id(order_id);
		mo1.setMenu_order(menu_order);
		System.out.println("1111111111111");
		user.addMenuOrdered(mo1);
		System.out.println("1111111111111");
		///for checking the menu_ordered table
		ArrayList<Menu_Ordered> ordered=user.getAllMenuOrdered();
		System.out.println("Menu_Ordered List");
		for(Menu_Ordered md:ordered)
		{
			System.out.println("Menu Ordered:"+md);
		}
		//anuja
		//anuja
	//add into order and cook table the order
		CookQueueService cq= new CookQueueService();
		cq.insertOrder(session.getAttribute("cook").toString(),session.getAttribute("startTime").toString(),session.getAttribute("endTime").toString(),session.getAttribute("cookDate").toString());
		//user.addOrder(session.getAttribute("email").toString(),session.getAttribute("item").toString(),session.getAttribute("price").toString(),session.getAttribute("quantity").toString(),session.getAttribute("pickDate").toString(),session.getAttribute("pickTime").toString(),session.getAttribute("startTime").toString(),session.getAttribute("endTime").toString(),session.getAttribute("cook").toString() );
		
		//send confirmation email
		UserService us=new UserService();
		ArrayList<Menu_Ordered> menu_order1=us.getAllMenuOrdered();
		Menu_Ordered send=new Menu_Ordered();
		for(Menu_Ordered m1:menu_order1)
		{
			System.out.println("menu_orders:"+m1.getMenu_order());
			for(Entry<String, Integer> e:m1.getMenu_order().entrySet())
			{
				System.out.println("Menu:"+e.getKey()+" Count:"+e.getValue());
				if(m1.getOrder_id()==order_id)
				{
					send=m1;
					break;
				}	
			}
		}	
us.sendmail(email, "CONFIRM",order_id,send,session.getAttribute("totalPrice").toString());

model.addObject("total_price",session.getAttribute("totalPrice").toString());

		return  model;
	
		
	}
	
	//user cancel
	@RequestMapping(value="/cancelUserOrder.html",method=RequestMethod.POST)
	public ModelAndView cancelUserOrder(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model=new ModelAndView();
		String array=request.getParameter("array");
		System.out.println("items is:"+array.toString());
		String[] items=array.split(",");
		for(String s: items)
		{

			HttpSession session=request.getSession();
			String email=session.getAttribute("email").toString();
			int orderId=Integer.parseInt(s);
			UserService us=new UserService();
			us.cancelOrder(email,orderId);
		
		}
		model.setViewName("menu");
		return model;
	}
	
	//for viewing and cancelling the orders-User
	@RequestMapping(value="/viewOrders.html",method=RequestMethod.POST)
	public ModelAndView OrdersToCancel(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s=request.getSession();
		String username=s.getAttribute("email").toString();
		UserService us=new UserService();
		
		List<Order_details> od=us.getOrdersForUser(username);
		List<Order_details> cancel=new ArrayList<Order_details>();
		List<Order_details> delivered_progress=new ArrayList<Order_details>();
		
		for(Order_details o:od)
		{
			if(o.getStatus().equals("placed"))
			{
				cancel.add(o);
			}
			else
				delivered_progress.add(o);
			System.out.println("o:"+o);
		}
		
		
		menuService mes=new menuService();
		
		HashMap<Integer, HashMap<String,Integer>> map=new HashMap<Integer, HashMap<String,Integer>>();

		List<Order_details> frontList=new ArrayList<Order_details>();
		int i=0;
		for(Order_details o : cancel)
		{
			
			ArrayList<Menu_Ordered> e=new ArrayList<Menu_Ordered>();
			e=mes.getDetails(o.getOrder_id());
			System.out.println("here are the so called details"+ e);
			//
			Order_details test= new Order_details();
			
			test.setOrder_id((o.getOrder_id()));
			test.setPickDate(o.getPickDate());
			test.setPickTime(o.getPickTime());
			test.setStatus(o.getStatus().toString());
			test.setUserId(o.getUserId().toString());
			test.setEnd_time(o.getEnd_time());
			test.setStart_time(o.getStart_time());
			
			System.out.println(o);
			frontList.add(test);
			
			map.put(i,e.get(0).getMenu_order());
			i++;
			
		}
		
		HashMap<Integer, HashMap<String,Integer>> map_delivered=new HashMap<Integer, HashMap<String,Integer>>();

		List<Order_details> frontList_delivered=new ArrayList<Order_details>();
		int delivered=0;
		for(Order_details o : delivered_progress)
		{
			
			ArrayList<Menu_Ordered> e=new ArrayList<Menu_Ordered>();
			e=mes.getDetails(o.getOrder_id());
			System.out.println("here are the so called details"+ e);
			//
			Order_details test= new Order_details();
			
			test.setOrder_id((o.getOrder_id()));
			test.setPickDate(o.getPickDate());
			test.setPickTime(o.getPickTime());
			test.setStatus(o.getStatus().toString());
			test.setUserId(o.getUserId().toString());
			test.setEnd_time(o.getEnd_time());
			test.setStart_time(o.getStart_time());
			
			System.out.println(o);
			frontList_delivered.add(test);
			
			map_delivered.put(delivered,e.get(0).getMenu_order());
			delivered++;
			
		}
		
		
		
		
		
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("frontList",frontList);
		mv.addObject("backList", map);
		mv.addObject("frontList_delivered",frontList_delivered);
		mv.addObject("backList_delivered", map_delivered);
		mv.setViewName("showCancellation");
		return mv;
	}
	
	
}
