package com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="menu_temp")
public class Menu_temp {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int menu_id; 
	private String menu_name;
	private String category;
	
	public Menu_temp() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Menu_temp(int menu_id, String menu_name, String category) {
		super();
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.category = category;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Menu_temp [menu_id=" + menu_id + ", menu_name=" + menu_name + ", category=" + category + "]";
	}
	
	

}
