package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.MenuTempDAO;
import com.project.model.Menu_temp;

@Service
public class MenuTempService {
	
	@Autowired
	MenuTempDAO mtd=new MenuTempDAO();
	
	@Transactional
	public List<Menu_temp> showAllMenu()
	{
		List<Menu_temp> menu=mtd.getAllMenu();
		return menu;
	}

}
