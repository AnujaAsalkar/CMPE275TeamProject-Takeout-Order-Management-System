package com.project.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.type.BlobType;
import org.hibernate.validator.constraints.Range;
@Entity
public class Menu {
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order_details order1;
	
	public Order_details getOrder() {
		return order1;
	}
	public void setOrder(Order_details order1) {
		this.order1 = order1;
	}
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=0, max=999)
	private int menu_id;
	private String menu_status;
	//@Column(unique=true)
	private String item_name;
	private String unitPrice;//eg:$5.5
	//@Enumerated(EnumType.STRING)
    private String category;
    private String quantity;
    
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		  this.category = category;
		 }
	@Range(min=0)
	private int calories;
	private Currency unit_price;//doubtful
	@Range(min=1, max=10)
	private int preparation_time;
	private byte[] image;
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_status() {
		return menu_status;
	}
	public void setMenu_status(String menu_status) {
		this.menu_status = menu_status;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public Currency getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Currency unit_price) {
		this.unit_price = unit_price;
	}
	public int getPreparation_time() {
		return preparation_time;
	}
	public void setPreparation_time(int preparation_time) {
		this.preparation_time = preparation_time;
	}
	@Override
	public String toString() {
		return "Menu [order1=" + order1 + ", menu_id=" + menu_id + ", menu_status=" + menu_status + ", item_name="
				+ item_name + ", unitPrice=" + unitPrice + ", category=" + category + ", calories=" + calories
				+ ", unit_price=" + unit_price + ", preparation_time=" + preparation_time + ", image="
				+ Arrays.toString(image) + "]";
	}
	

private Integer popularity;
    
	public Integer getPopularity() {
		return popularity;
	}
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	
}
