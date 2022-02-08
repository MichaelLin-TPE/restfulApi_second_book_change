package net.apiguides.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
public class FriendData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "my_uid")
	private String myUid;
	
	@Column(name = "friend_uid")
	private String friendUid;
	
	@Column(name = "friend_photo_url")
	private String friendPhotoUrl;

	public FriendData() {
		
	}
	
	
	public FriendData(String myUid, String friendUid, String friendPhotoUrl) {
		super();
		this.myUid = myUid;
		this.friendUid = friendUid;
		this.friendPhotoUrl = friendPhotoUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMyUid() {
		return myUid;
	}

	public void setMyUid(String myUid) {
		this.myUid = myUid;
	}

	public String getFriendUid() {
		return friendUid;
	}

	public void setFriendUid(String friendUid) {
		this.friendUid = friendUid;
	}

	public String getFriendPhotoUrl() {
		return friendPhotoUrl;
	}

	public void setFriendPhotoUrl(String friendPhotoUrl) {
		this.friendPhotoUrl = friendPhotoUrl;
	}
	
	
	
	
}
