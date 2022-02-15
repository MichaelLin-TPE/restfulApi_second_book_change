package net.apiguides.springboot.entity;

import javax.persistence.Entity;

public class SearchData {

	private String uid;
	
	private String name;

	public SearchData() {
		
	}
	
	public SearchData(String name,String uid) {
		super();
		this.name = name;
		this.uid = uid;
	}
	
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
