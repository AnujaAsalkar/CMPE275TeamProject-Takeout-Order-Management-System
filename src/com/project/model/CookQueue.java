package com.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CookQueue {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int queueId;
	
	private Date start_time;
	@Temporal(TemporalType.TIME)
	private Date end_time;
	
	private int cook_id;
	//@Temporal(TemporalType.TIME)
	private String cookDate;
	public CookQueue() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CookQueue(int queueId, Date pickup_date, Date pickup_time, Date start_time, Date end_time, int cook_id,String cookDate) {
		super();
		this.queueId = queueId;
		this.cookDate=cookDate;
		this.start_time = start_time;
		this.end_time = end_time;
		this.cook_id = cook_id;
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
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
	public String getcookDate() {
		return cookDate;
	}
	public void setcookDate(String cookDate) {
		this.cookDate = cookDate;
	}
	public int getCook_id() {
		return cook_id;
	}
	public void setCook_id(int cook_id) {
		this.cook_id = cook_id;
	}

	@Override
	public String toString() {
		return "CookQueue [queueId=" + queueId + ", start_time=" + start_time + ", end_time=" + end_time + ", cook_id=" + cook_id + "]";
	}
	
	
	

}
