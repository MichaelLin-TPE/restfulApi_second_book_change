package net.apiguides.springboot.entity;

public class FriendList {

	
	private String uid;
	
	private String photoUrl;

	public FriendList() {
		
	}
	
	public FriendList(String uid, String photoUrl) {
		super();
		this.uid = uid;
		this.photoUrl = photoUrl;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	
}
