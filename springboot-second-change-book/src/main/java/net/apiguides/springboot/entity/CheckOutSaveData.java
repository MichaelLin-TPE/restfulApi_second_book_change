package net.apiguides.springboot.entity;

import java.awt.SplashScreen;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class CheckOutSaveData{
	
	
	private long id;


	private String userEmail;
	

	private String uploaderUid;
	

	private String myUid;
	

	private boolean isAllSelected;
	

	private String orderId;
	
	private int shipmentFee;
	
	private String shipmentWay;
	
	private ArrayList<CheckOutProductList> productLists;
	
	public CheckOutSaveData() {
		
	}
	
	
	public CheckOutSaveData(String userEmail, String uploaderUid, String myUid, boolean isAllSelected,
			String orderId,int shipmentFee,String shipmentWay,ArrayList<CheckOutProductList> productLists) {
		super();
		this.orderId = orderId;
		this.userEmail = userEmail;
		this.uploaderUid = uploaderUid;
		this.myUid = myUid;
		this.isAllSelected = isAllSelected;
		this.productLists = productLists;
		this.shipmentFee = shipmentFee;
		this.shipmentWay = shipmentWay;
	}

	
	
	public int getShipmentFee() {
		return shipmentFee;
	}


	public void setShipmentFee(int shipmentFee) {
		this.shipmentFee = shipmentFee;
	}


	public String getShipmentWay() {
		return shipmentWay;
	}


	public void setShipmentWay(String shipmentWay) {
		this.shipmentWay = shipmentWay;
	}


	public ArrayList<CheckOutProductList> getProductLists() {
		return productLists;
	}


	public void setProductLists(ArrayList<CheckOutProductList> productLists) {
		this.productLists = productLists;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUploaderUid() {
		return uploaderUid;
	}

	public void setUploaderUid(String uploaderUid) {
		this.uploaderUid = uploaderUid;
	}

	public String getMyUid() {
		return myUid;
	}

	public void setMyUid(String myUid) {
		this.myUid = myUid;
	}

	public boolean isAllSelected() {
		return isAllSelected;
	}

	public void setAllSelected(boolean isAllSelected) {
		this.isAllSelected = isAllSelected;
	}


	
}
