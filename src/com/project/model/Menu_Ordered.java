package com.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Menu_Ordered implements Serializable {
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	int menu_ordered_id;
	@Column
	int order_id;
	HashMap<String, Integer> menu_order;
	public Menu_Ordered()
	{
		
	}
	
	public Menu_Ordered(int order_id, HashMap<String, Integer> menu_order) {
		super();
		this.order_id = order_id;
		this.menu_order = menu_order;
	}


	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public HashMap<String, Integer> getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(HashMap<String, Integer> menu_order) {
		this.menu_order = menu_order;
	}

	@Override
	public String toString() {
		return "Menu_Ordered [menu_ordered_id=" + menu_ordered_id + ", order_id=" + order_id + ", menu_order="
				+ menu_order + "]";
	}

	
}