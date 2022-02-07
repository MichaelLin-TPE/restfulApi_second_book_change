package net.apiguides.springboot.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "check_out")
public class CheckOutData implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "uploader_uid")
	private String uploaderUid;
	
	@Column(name = "my_uid")
	private String myUid;
	
	@Column(name = "is_all_selected")
	private boolean isAllSelected;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "shipment_fee")
	private int shipmentFee;
	
	@Column(name = "shipment_way")
	private String shipmentWay;
	
	
	public CheckOutData() {
		
	}
	
	
	public CheckOutData(String userEmail, String uploaderUid, String myUid, boolean isAllSelected,
			String orderId,int shipmentFee,String shipmnetWay) {
		super();
		this.orderId = orderId;
		this.userEmail = userEmail;
		this.uploaderUid = uploaderUid;
		this.myUid = myUid;
		this.isAllSelected = isAllSelected;
		this.shipmentFee = shipmentFee;
		this.shipmentWay = shipmnetWay;
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
