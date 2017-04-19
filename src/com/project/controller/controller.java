package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.type.BlobType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Category;
import com.project.model.CookQueue;
import com.project.model.Menu;
import com.project.model.Menu_Ordered;
import com.project.model.Order_details;
import com.project.model.User;
import com.project.service.menuService;
import com.project.service.CookQueueService;
import com.project.service.UserService;


@Controller
public class controller {

	UserService userService=new UserService();
	
	menuService menuService= new menuService();
	
	//render admin login page
	@RequestMapping(value="/renderAdmin", method=RequestMethod.GET)
	public String showLogin()
	{
		System.out.println("inside render admin");
		//return new ModelAndView("signup.jsp");
		return "loginAdmin";
	}
	
	
	@RequestMapping(value="/getprofilehtml", method=RequestMethod.POST)
	public String adminLogin(@RequestParam("email") String id,@RequestParam("password") String password,ModelMap model) {
		 
		System.out.println(id+"wtf"+password);
		if(id.equalsIgnoreCase("sachin@gmail.com") && password.equalsIgnoreCase("sac"))
		{
			return "adminHome";
		}
		return "error";
		
	}
	//ADD ITEMS INTO MENU
	@RequestMapping(value="/addMenu", method=RequestMethod.POST)
	public String addMenu(@RequestParam("itemName") String itemName,@RequestParam("price") String price,@RequestParam("category") String category,@RequestParam("calories") int calories,@RequestParam("time") int prepTime,@RequestParam("status") String status,@RequestParam("pic") MultipartFile image,ModelMap model) {
		
		
		menuService.addMenu(itemName,price,category,status,prepTime,calories,image,"1");
		Menu menu=menuService.getMenu(itemName);
		byte [] b=menu.getImage();
		 try{
	         //for local 
			 FileOutputStream fos = new FileOutputStream("/opt/tomcat/webapps/275cmpe/images/"+menu.getMenu_id()+".jpg"); 
			 //opt/tomcat/webapps/275cmpe/images
			 //for shikha's
	           // FileOutputStream fos = new FileOutputStream("opt/tomcat/webapps/275cmpe/images"+menu.getMenu_id()+".jpg");
	           //for sindhu's 
	           // FileOutputStream fos = new FileOutputStream("home/ubuntu/apache-tomcat-8.0.33/apache-tomcat-8.0.33/webapps/images"+menu.getMenu_id()+".jpg");
	            fos.write(b);
	            fos.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		// System.out.print(b);
		// model.addAttribute("file","./images/"+itemName+".jpg");
		return "adminHome";
		
	}

	//Deactivate MENU ITEM
	@RequestMapping(value="/deactivate", method=RequestMethod.POST)
	public ModelAndView deactivateMenu(@RequestParam("itemName") String itemName,ModelMap model) {
		 
		String res=menuService.deactivateMenu(itemName);
		if(res=="right")
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("adminHome");
		return mv;
		}
		else
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("adminHome");
			mv.addObject("msg4", "Item does not exist");
			return mv;	
		}
		
	}
	
	//Activate MENU ITEM
	@RequestMapping(value="/activate", method=RequestMethod.POST)
	public ModelAndView activateMenu(@RequestParam("itemName") String itemName,ModelMap model) {
		 
		String res=menuService.activateMenu(itemName);
		if(res=="right")
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("adminHome");
		return mv;
		}
		else
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("adminHome");
			mv.addObject("msg3", "Item does not exist");
			return mv;	
		}
	}
	//EDIT MENU ITEM
	@RequestMapping(value="/editMenu.html",method=RequestMethod.POST)
	public ModelAndView addNewUser(@RequestParam("id") int id)
	{
		ModelAndView model=new ModelAndView();
		Menu menu=menuService.editMenu(id);
		if(menu==null)
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("adminHome");
			mv.addObject("msg2", "Item does not exist");
			return mv;
		}
		model.addObject("id", menu.getMenu_id());
		model.addObject("itemName", menu.getItem_name());
		model.addObject("price", menu.getUnitPrice());
		model.addObject("calories", menu.getCalories());
		model.addObject("category", menu.getCategory());
		model.addObject("time", menu.getPreparation_time());
		model.addObject("status", menu.getMenu_status());
		byte [] b=menu.getImage();
		 try{
			 //for local
	           FileOutputStream fos = new FileOutputStream("/opt/tomcat/webapps/275cmpe/images/"+menu.getMenu_id()+".jpg");
			 //for shikha's
			// FileOutputStream fos = new FileOutputStream("opt/tomcat/webapps/275cmpe/images"+menu.getMenu_id()+".jpg");
			 //for sindhus
			 //FileOutputStream fos = new FileOutputStream("home/ubuntu/apache-tomcat-8.0.33/apache-tomcat-8.0.33/webapps/images"+menu.getMenu_id()+".jpg");
			 	fos.write(b);
	            
	            fos.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }

		
		model.addObject("file","./images/"+menu.getMenu_id()+".jpg");
		model.setViewName("editMenu");
		return model;
	}
	
	//UPDATE MENU ITEM
		@RequestMapping(value="/update", method=RequestMethod.POST)
		public String updateMenu(@RequestParam("id") int id,@RequestParam("itemName") String itemName,@RequestParam("price") String price,@RequestParam("category") String category,@RequestParam("calories") int calories,@RequestParam("time") int prepTime,@RequestParam("status") String status,@RequestParam("pic") MultipartFile image,ModelMap model) throws IOException {
			
			if(image.getSize() == 0)
			 {
				 Menu menu=menuService.editMenu(id);
				 menuService.updateMenu(id,itemName,price,category,status,prepTime,calories,menu.getImage(),"1");
			 } else
				{
		
				 menuService.updateMenu(id,itemName,price,category,status,prepTime,calories,image.getBytes(),"1");
				}
			 Menu menu=menuService.getMenu(itemName);
			//for local
			File file=new File("/opt/tomcat/webapps/275cmpe/images/"+menu.getMenu_id()+".jpg");
			  file.delete();
			 byte [] b=menu.getImage();
			 try{
				 //for local
		           FileOutputStream fos = new FileOutputStream("/opt/tomcat/webapps/275cmpe/images/"+menu.getMenu_id()+".jpg");
		             fos.write(b);
		            
		            fos.close();
		        }catch(Exception e){
		            e.printStackTrace();
		        }
			return "adminHome";		
		}
		
		
		//for resetting order queue
		@RequestMapping(value="/resetMenu.html",method=RequestMethod.POST)
		public ModelAndView resetTable()
		{
			ModelAndView model=new ModelAndView();
			System.out.println("inside reset table");
			CookQueueService cqs=new CookQueueService();
			cqs.reset();
			
			menuService ms=new menuService();
			ms.resetMenu();
			
					model.setViewName("adminHome");
					return model;
		}
		
		//adding into cook
		@RequestMapping(value="/setMenu.html",method=RequestMethod.POST)
		public void setTable()
		{
			System.out.println("inside reset table");
			CookQueueService cqs=new CookQueueService();
			CookQueue cq=new CookQueue();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			
			try {
				Date date = dateFormat.parse("2016-10-10");
				cq.setCook_id(1);
	
				cq.setStart_time(timeFormat.parse("06:00"));
				cq.setEnd_time(timeFormat.parse("06:10"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			cqs.addNewCook(cq);
		}
		
		
		//ORDER HISTORYYYYYYYYYYYYYYYY
		@RequestMapping(value="/orderHistory.html",method=RequestMethod.POST)
		public ModelAndView orderHistory(HttpServletRequest request,HttpServletResponse response)
		{
			ModelAndView model=new ModelAndView();
			HttpSession session=request.getSession();
			String from_date=request.getParameter("from_date");
			String to_date=request.getParameter("to_date");
			String sortOrder=request.getParameter("OrderTime");
			String sortStart=request.getParameter("StartTime");
			if(from_date!=null && to_date!=null)
			{
				
			if(from_date.compareTo(to_date)>0)
			{
				model.setViewName("adminHome");
				model.addObject("msg1", "To Date is before After Date Please Correct");
				return model;
			}
			else
			{
				session.setAttribute("fromDate", from_date);
				session.setAttribute("toDate", to_date);
			}
			}
			
			menuService ms=new menuService();
			
			ArrayList<Order_details> od= ms.getHistory(session.getAttribute("fromDate").toString(),session.getAttribute("toDate").toString());
			if(sortStart != null)
			{
				System.out.println("ORDER BY START TIME");
				Collections.sort(od, new Comparator<Order_details>(){
			
				@Override
				public int compare(Order_details o1, Order_details o2) {
					// TODO Auto-generated method stub
					return o1.getStart_time().compareTo(o2.getStart_time());
				}
			});
			}
			if(sortOrder!=null)
			{
				System.out.println("ORDER BY ORDER TIME");
				Collections.sort(od, new Comparator<Order_details>(){
			
				@Override
				public int compare(Order_details o1, Order_details o2) {
					// TODO Auto-generated method stub
					return o1.getOrderTime().compareTo(o2.getOrderTime());
				}
			});
			}	
			//new code
			menuService mes=new menuService();
			
			//
			
			
			
			
			List <Order_details> frontList=new ArrayList<Order_details>();
			LinkedHashMap <Order_details,HashMap<String,Integer>> map= new LinkedHashMap<Order_details,HashMap<String,Integer>>();
			
			for(Order_details o : od)
			{
				
				ArrayList<Menu_Ordered> e=new ArrayList<Menu_Ordered>();
				e=mes.getDetails(o.getOrder_id());
				System.out.println("here are the so called details"+ e);
				//
				Order_details test= new Order_details();
				
				test.setOrder_id(o.getOrder_id());
				test.setPickDate(o.getPickDate());
				test.setPickTime(o.getPickTime());
				test.setStatus(o.getStatus().toString());
				test.setUserId(o.getUserId().toString());
				test.setEnd_time(o.getEnd_time());
				test.setStart_time(o.getStart_time());
				test.setOrderTime(o.getOrderTime());
				
				System.out.println("is it right"+o.getStart_time());
				//frontList.add(test);
				
			//	map.put(o.getOrder_id(),e.get(0).getMenu_order());
				map.put(test, e.get(0).getMenu_order());
			}
			System.out.println("can u believe it??"+map);
		
			model.addObject("orderHistory",frontList);
			model.addObject("backList",map);
			model.setViewName("orderHistory");
			return model;
			
		
		}

		//Popularity Report
				@RequestMapping(value="/popularityReport.html",method=RequestMethod.POST)
				public ModelAndView popularityReport(HttpServletRequest request, HttpServletResponse response)
				{
					HashMap<String,Integer> menu_count=new HashMap<String,Integer>();
					
				//	HashMap<String, > menu_category=new HashMap<String, String>();
					
					menuService mService=new menuService();
					List<Menu> menus= mService.showAllMenu();
					
					for(Menu m:menus)
					{
						if(m.getMenu_status().equalsIgnoreCase("TRUE"))
						{
							menu_count.put(m.getItem_name(),0);
							
						}
					}
							
					UserService us=new UserService();
					//List<Menu_Ordered>order_menu=new ArrayList<Menu_Ordered>();
					
					ArrayList<Menu_Ordered> menu_order=us.getAllMenuOrdered();
					Integer count;
					for(Menu_Ordered m1:menu_order)
					{
						System.out.println("menu_orders:"+m1.getMenu_order());
						for(Entry<String, Integer> e:m1.getMenu_order().entrySet())
						{
							System.out.println("Menu:"+e.getKey()+" Count:"+e.getValue());
							
							count=menu_count.get(e.getKey());
							count++;
							menu_count.put(e.getKey(), count);		
						}
					}
					
					System.out.println("After incrementing the count");
					
					List<Menu> drink_popular=new ArrayList<Menu>();
					List<Menu> appetizer_popular=new ArrayList<Menu>();
					List<Menu> main_course_popular=new ArrayList<Menu>();
					List<Menu> dessert_popular=new ArrayList<Menu>();
					
					menuService mService1=new menuService();
					List<Menu> menus1= mService1.showAllMenu();
					
					for(Menu me:menus1)
					{
						for(Entry<String, Integer> e:menu_count.entrySet())
						{
						
							if(me.getItem_name().equals(e.getKey()))
							{
								//me.setPopularity(e.getValue());
								System.out.println("menu m:"+e.getKey()+" value:"+e.getValue());
								me.setPopularity(e.getValue());
								mService1.setPopularity(me);
								
							}
						}
					
					}
					
					
					
					System.out.println("after setting popularity:");
					for(Menu me2:menus1)
					{
						//System.out.println("Menus category:"+me2.getPopularity());
						if(me2.getMenu_status().equalsIgnoreCase("TRUE"))
						{
							if(me2.getCategory().equalsIgnoreCase("DRINK"))
							{
								drink_popular.add(me2);
							}
							else if(me2.getCategory().equalsIgnoreCase("APPETIZER"))
							{
								appetizer_popular.add(me2);	
							}
							else if(me2.getCategory().equalsIgnoreCase("MAIN COURSE"))
							{
								main_course_popular.add(me2);				
							}
							else
							{
								dessert_popular.add(me2);	
							}
						}
					}
					
					 Collections.sort(drink_popular, new Comparator<Menu>() {
					        @Override public int compare(Menu p1, Menu p2) {
					            return p2.getPopularity()-p1.getPopularity(); // Descending
					        }

					    });
					 Collections.sort(dessert_popular, new Comparator<Menu>() {
					        @Override public int compare(Menu p1, Menu p2) {
					            return p2.getPopularity()-p1.getPopularity(); // Descending
					        }

					    });
					 Collections.sort(main_course_popular, new Comparator<Menu>() {
					        @Override public int compare(Menu p1, Menu p2) {
					            return p2.getPopularity()-p1.getPopularity(); // Descending
					        }
					    });
					 Collections.sort(appetizer_popular, new Comparator<Menu>() {
					        @Override public int compare(Menu p1, Menu p2) {
					            return p2.getPopularity()-p1.getPopularity(); // Descending
					        }

					    });
					 
					 System.out.println("appetizer");
					 for(Menu m123:appetizer_popular)
					 {
						 System.out.println("Popularity:"+m123.getPopularity());
					 }
					 System.out.println("drinks");
					 for(Menu m123:drink_popular)
					 {
						 System.out.println("Popularity:"+m123.getPopularity());
					 }
					 System.out.println("main_course");
					 for(Menu m123:main_course_popular)
					 {
						 System.out.println("Popularity:"+m123.getPopularity());
					 }
					 System.out.println("dessert");
					 for(Menu m123:dessert_popular)
					 {
						 System.out.println("Popularity:"+m123.getPopularity());
					 }

					 
					 ModelAndView mv=new ModelAndView();	
						mv.addObject("drinks", drink_popular);
						mv.addObject("appetizers", appetizer_popular);
						mv.addObject("main_courses", main_course_popular);
						mv.addObject("desserts", dessert_popular);
						//mv.addObject("s",session);
						mv.setViewName("PopularityReport");	
						return mv;
					 
				}

}
