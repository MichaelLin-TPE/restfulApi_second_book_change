package net.apiguides.springboot.entity;

public class ResponseData {

	private int result;
	
	private String message;
	
	public ResponseData() {
		
	}

	public ResponseData(int result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
