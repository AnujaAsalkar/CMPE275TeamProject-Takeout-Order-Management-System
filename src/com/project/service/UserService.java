package com.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.DAO.UserDAO;
import com.project.DAO.orderDAO;
import com.project.model.Menu_Ordered;
import com.project.model.Order_details;
import com.project.model.User;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO=new UserDAO();
	
	@Transactional
	public void addNewUser(User user)//String fname,String lname,String email, String password,String pno,Integer vcode)
	{
		System.out.println("in userservice:"+user);
		try{
			/*user.setFirst_name(fname);
			user.setLast_name(lname);
			user.setEmail_id(email);
			user.setPassword(password);
			user.setPhone_number(pno);
			user.setVerification_code(vcode);
			user.setEnabled(true);*/
		userDAO.addUser(user);
		//return;
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
	}
	
	public int userLogin(User user) {
		int login = userDAO.searchUser(user);
		return login;
	}

/*	public void placeOrder(String item, String qty, Date date, float price, String pickTime) {
		// TODO Auto-generated method stub
		Order_details order=new Order_details();
		order.setMenu_items(item);
		order.setQty(qty);
		order.setOrderTime(date);
		order.setPrice(price);
		orderDAO placeOrder=new orderDAO();
		placeOrder.placeOrder(order);
		
	}*/
	
	
	//send email
	public int sendmail(String email, String action,int order_id,Menu_Ordered menu_order1,String totalprice)
	{
		Random rn = new Random();
		String BODY=" ";
		int verification;
		SendGrid.Email emaill = new SendGrid.Email();
		emaill.addTo(email);
		emaill.setFrom("shikha.kukreja@sjsu.edu");
		if(action.equalsIgnoreCase("VERIFY"))
		{
			verification= rn.nextInt(100000);
			BODY=BODY+"Your verification code is "+verification+" please put the code to complete the signup process";//it generates random number between 0 and 100000
			SendGrid sendgrid = new SendGrid("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");
			//veremail.put(verification,email);
			emaill.setSubject("VERIFICATION CODE");
			emaill.setHtml(BODY);
			try {
				   SendGrid.Response response = sendgrid.send(emaill);
			} catch (SendGridException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			}
			System.out.println("Email Sent!");
			return verification;
		}
		else if(action.equalsIgnoreCase("CONFIRM"))
		{
			BODY=BODY+"This is confirmation mail for Order Id:"+order_id;
			BODY=BODY+" The order details as follows:";
			HashMap<String, Integer> men=menu_order1.getMenu_order();
			
				for(Entry<String, Integer> e:men.entrySet())
				{
					System.out.println("Menu:"+e.getKey()+" Count:"+e.getValue());
					BODY=BODY+e.getKey()+"-"+e.getValue();
					BODY=BODY+" The total price: "+totalprice;
				}
			
					
			SendGrid sendgrid = new SendGrid("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");
			//veremail.put(verification,email);
			emaill.setSubject("CONFIRMATION MAIL");
			emaill.setHtml(BODY);
			try {
				   SendGrid.Response response = sendgrid.send(emaill);
			} catch (SendGridException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			}
			System.out.println("Email Sent!");
			return 1;
		}
		else
		{
			return 1;
		}
		
		
		  
		 
	}
	//check user exists
	public User getUser(User user)
	{
		User user1 = null;
		System.out.println("in userservice:"+user);
		try{
		 user1=userDAO.getUser(user);
		
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
		return user1;
	}

	/*public void addOrder(String email, String itemSet, String priceSet, String qtySet, String pickDate,
			String pickTime, String startTime, String endTime, String cook) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat form= new SimpleDateFormat("HH:mm");
		Order_details od= new Order_details();
		od.setMenu_items(itemSet);
		od.setUserId(email);
		od.setPrice(priceSet);
		od.setQty(qtySet);
		od.setOrderTime(form.parse(pickTime));
		od.setOrderDate(pickDate);
		od.setStatus("placed");
		od.setStart_time(form.parse(startTime));
		od.setEnd_time(form.parse(endTime));
		od.setCook(Integer.parseInt(cook));
		orderDAO ord=new orderDAO();
		ord.addOrder(od);
		
	}*/
	
	//ADD NEW ORDER
	public void addOrder(Order_details od)
	{
		System.out.println("inside addOrder:"+od);
		orderDAO ord=new orderDAO();
		ord.addOrder(od);
	}
	//for finding recent order id
		public int findRecentOrderId() {
			// TODO Auto-generated method stub
			orderDAO od=new orderDAO();
			int order_id=od.getLastOrderId();
			return order_id;
		}
		
	public void cancelOrder(String email, int orderId) {
		orderDAO od=new orderDAO();
		od.cancelOrder(email,orderId);
		
	}

	//getting from menu_ordered table
		public ArrayList<Menu_Ordered> getAllMenuOrdered() {
			// TODO Auto-generated method stub
			
			System.out.println("inside getAllMenuOrdered:");
			orderDAO ord=new orderDAO();
			ArrayList<Menu_Ordered> mo=ord.getAllMenuOrdered();
			return mo;
			
		}

		//for insering into menu ordered table
		public void addMenuOrdered(Menu_Ordered mo) {
			// TODO Auto-generated method stub

			System.out.println("inside addOrder:"+mo);
			orderDAO ord=new orderDAO();
			ord.addMenuOrder(mo);
			
			
		}
	
		//get Order details given a user
		public List<Order_details> getOrdersForUser(String username) {
			// TODO Auto-generated method stub
			System.out.println("inside getOrderForUser:");
			orderDAO ord=new orderDAO();
			ArrayList<Order_details> mo=ord.getOrdersForUser(username);
			return mo;
			
		}

		

}
