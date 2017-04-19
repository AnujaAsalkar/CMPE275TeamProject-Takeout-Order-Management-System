/*package com.project.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;

@Entity
public class Order_details {

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
	private String menu_items = new String();
	public String getMenu_items() {
		return menu_items;
	}
	public void setMenu_items(String item) {
		this.menu_items = item;
	}
	private String qty = new String();
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	private String status = new String();
	public String getStatus() {

		return status;

	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String userId = new String();
	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		this.userId = id;
	}
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=0)
	private int order_id;
	public int getOrder_id() {
		return this.order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
//	@Temporal(TemporalType.TIMESTAMP)


	

	@Temporal(TemporalType.TIME)
	private Date pickTime; 
	public Date getPickTime() {

		return this.pickTime;
	}


	

	public void setOrderTime(Date pickTime) {
		this.pickTime = pickTime;
	}
	//@Temporal(TemporalType.TIME)

	private String pickDate; 
	public String getPickDate() {
		return this.pickDate;
	}
	public void setOrderDate(String pickDate) {
		this.pickDate = pickDate;
	}

	private int cook;
	
	public int getCook() {
		return this.cook;
	}
	public void setCook(int cook) {
		this.cook = cook;
	}
	private String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getCook_id() {
		return cook_id;
	}
	public void setCook_id(int cook_id) {
		this.cook_id = cook_id;
	}
	@Temporal(TemporalType.TIME)
	private Date start_time;
	@Temporal(TemporalType.TIME)
	private Date end_time;
	private int cook_id;
	
}*/


package com.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;

import com.project.DAO.orderDAO;

@Entity
public class Order_details {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=0)
	private int order_id;
	//private String menu_items = new String();
	//private String qty;// = new String();
	private String status;// = new String();
	private String userId; // = new String();
	@Temporal(TemporalType.TIME)
	private Date pickTime;
	private String pickDate; 
	private String orderTime;
	private String price;
	@Temporal(TemporalType.TIME)
	private Date start_time;
	@Temporal(TemporalType.TIME)
	private Date end_time;
	private int cook_id;

	
	/*@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable
	(
		name="Order_Menu",
		joinColumns=@JoinColumn(name="order_id",referencedColumnName="order_id"),
		inverseJoinColumns={
				@JoinColumn(name="menu_name",referencedColumnName="menu_name"),
				@JoinColumn(name="menu_quantity",referencedColumnName="quantity")
		}
	)
	private List<Menu_Ordered> mo=new ArrayList<Menu_Ordered>();
	public List<Menu_Ordered> getMo() {
		return mo;
	}
	public void setMo(List<Menu_Ordered> mo) {
		this.mo = mo;	
	}
*/
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getPickTime() {
		return pickTime;
	}
	public void setPickTime(Date pickTime) {
		this.pickTime = pickTime;
	}
	public String getPickDate() {
		return pickDate;
	}
	public void setPickDate(String pickDate) {
		this.pickDate = pickDate;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	public int getCook_id() {
		return cook_id;
	}
	public void setCook_id(int cook_id) {
		this.cook_id = cook_id;
	}
	@Override
	public String toString() {
		return "Order_details [order_id=" + order_id + ", status=" + status + ", userId=" + userId + ", pickTime="
				+ pickTime + ", pickDate=" + pickDate +  ", price=" + price + ", start_time="
				+ start_time + ", end_time=" + end_time + ", cook_id=" + cook_id +"]";
	}
	public ArrayList<Menu_Ordered> getOrderItem(int id) {
		// TODO Auto-generated method stub
		orderDAO o=new orderDAO();
		return o.getOrderItem(id);
	}

	

}