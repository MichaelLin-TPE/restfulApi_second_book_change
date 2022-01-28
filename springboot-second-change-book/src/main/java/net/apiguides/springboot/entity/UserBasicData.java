package net.apiguides.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="user_basic_data")
public class UserBasicData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "nickName")
	private String nickName;
	
	@Column(name = "account")
    private String account;
	
	@Column(name = "userUid")
    private String userUid;
	
	@Column(name = "tel")
    private String tel;
	
	@Column(name = "email")
    private String email;

    //上傳到網路上的照片, 拿網址, 所以是String
	@Column(name = "userPhotoUrl")
    private String userPhotoUrl;
	
	@Column(name = "follow")
    private int follow;
	
	@Column(name = "follower")
    private int follower;
	
	@Column(name = "bookCount")
    private int bookCount;
    
	
	
	
    public UserBasicData() {
    	
    }
    
	public UserBasicData(long id, String nickName, String account, String userUid, String tel, String email,
			String userPhotoUrl, int follow, int follower, int bookCount) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.account = account;
		this.userUid = userUid;
		this.tel = tel;
		this.email = email;
		this.userPhotoUrl = userPhotoUrl;
		this.follow = follow;
		this.follower = follower;
		this.bookCount = bookCount;
	}




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPhotoUrl() {
		return userPhotoUrl;
	}
	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}
	public int getFollow() {
		return follow;
	}
	public void setFollow(int follow) {
		this.follow = follow;
	}
	public int getFollower() {
		return follower;
	}
	public void setFollower(int follower) {
		this.follower = follower;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
    
    
	
	
	
}
