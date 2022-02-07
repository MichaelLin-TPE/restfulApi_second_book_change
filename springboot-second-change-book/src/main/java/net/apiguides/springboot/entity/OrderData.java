package net.apiguides.springboot.entity;



import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_data")
public class OrderData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "my_uid")
	private String myUid;
	
	@Column(name = "role")
	private int role;
	
	
	@Column(name = "order_id")
	private String orderId;
	
	public OrderData() {
		
	}

	public OrderData(int status,String myUid,int role,String orderId ) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.myUid = myUid;
		this.role = role;

	}
	
	


	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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
