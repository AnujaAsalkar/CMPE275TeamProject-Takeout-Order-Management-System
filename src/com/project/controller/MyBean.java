package com.project.controller;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.project.service.menuService;
 
@Component("myBean")
public class MyBean {
 
    public void printMessage() throws ParseException {
        System.out.println("I am called by Spring scheduler");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		Date time=new Date();
	
        menuService menu=new menuService();
        menu.updateStatus();
    }
}
