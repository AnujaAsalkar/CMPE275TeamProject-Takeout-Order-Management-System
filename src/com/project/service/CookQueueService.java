/*package com.project.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.CookQueueDAO;
import com.project.model.CookQueue;

@Service
public class CookQueueService {
	
	@Autowired
	CookQueueDAO cookQueueDAO=new CookQueueDAO();
	
	public void reset()
	{
		System.out.println("inside cook service");
		try
		{
			cookQueueDAO.resetCook();
		}
		catch(Exception e)
		{
			System.out.println("inside exception:");
			e.printStackTrace();
		}
	}
	
	public void addNewCook(CookQueue cq)
	{
		System.out.println("inside addNewCook");
		cookQueueDAO.insertIntoCook(cq);
	}
	
	public void check(Date d)
	{
		System.out.println("inside check service");
		cookQueueDAO.checkForDelivery(d);
	}

}
*/


package com.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.CookQueueDAO;
import com.project.model.CookQueue;

@Service
public class CookQueueService {
	
	@Autowired
	CookQueueDAO cookQueueDAO=new CookQueueDAO();
	
	public void reset()
	{
		System.out.println("inside cook service");
		try
		{
			cookQueueDAO.resetCook();
		}
		catch(Exception e)
		{
			System.out.println("inside exception:");
			e.printStackTrace();
		}
	}
	
	public void addNewCook(CookQueue cq)
	{
		System.out.println("inside addNewCook");
		cookQueueDAO.insertIntoCook(cq);
	}
	
	public void check(Date d)
	{
		System.out.println("inside check service");
		cookQueueDAO.checkForDelivery(d);
	}

	public ArrayList<String> check(String date, String time, int prepTime) {
		// TODO Auto-generated method stub
		return cookQueueDAO.checkEarliest(date,time,prepTime);
	}

	public void insertOrder(String id, String start, String end, String dated) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat form= new SimpleDateFormat("HH:mm");
		System.out.println("in cook insert"+formatter.parse(dated));
		
		CookQueue cq= new CookQueue();
		cq.setCook_id(Integer.parseInt(id));
		cq.setStart_time(form.parse(start));
		cq.setEnd_time(form.parse(end));
		cq.setcookDate(dated);
		CookQueueDAO c=new CookQueueDAO();
		c.insertIntoCook(cq);
		
	}

	public ArrayList<String> checkUserTime(String date, String time, int prepTime) {
		// TODO Auto-generated method stub
		return cookQueueDAO.checkEarliest(date,time,prepTime);
	}

}
