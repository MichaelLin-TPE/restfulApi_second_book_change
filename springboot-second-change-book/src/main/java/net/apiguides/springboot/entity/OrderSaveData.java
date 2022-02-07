package net.apiguides.springboot.entity;



import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class OrderSaveData {


	private long id;
	

	private int status;
	

	private String myUid;
	

	private String orderId;
	
	private int role;
	
	private ArrayList<CheckOutSaveData> checkOutList;
	
	public OrderSaveData() {
		
	}

	public OrderSaveData(int status,String myUid,String orderId,int role,ArrayList<CheckOutSaveData> checkOutList ) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.myUid = myUid;
		this.checkOutList = checkOutList;
		this.role = role;

	}
	
	


	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public ArrayList<CheckOutSaveData> getCheckOutList() {
		return checkOutList;
	}

	public void setCheckOutList(ArrayList<CheckOutSaveData> checkOutList) {
		this.checkOutList = checkOutList;
	}

	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	

	public String getMyUid() {
		return myUid;
	}

	public void setMyUid(String myUid) {
		this.myUid = myUid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
