package net.apiguides.springboot.entity;

import javax.persistence.Entity;

public class SearchData {

	private String name;

	public SearchData() {
		
	}
	
	public SearchData(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
