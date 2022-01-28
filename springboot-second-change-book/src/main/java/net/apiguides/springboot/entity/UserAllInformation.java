package net.apiguides.springboot.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserAllInformation {
	
	
	private String nickName;

    private String account;
	
    private String userUid;
	
    private String tel;

    private String email;

    private String userPhotoUrl;

    private int follow;
	
    private int follower;
	
    private int bookCount;
    
    private ArrayList<BookData> bookList;
    
    
    

	public UserAllInformation() {
	
	}

	public UserAllInformation(String nickName, String account, String userUid, String tel, String email,
			String userPhotoUrl, int follow, int follower, int bookCount, ArrayList<BookData> bookList) {
		super();
		this.nickName = nickName;
		this.account = account;
		this.userUid = userUid;
		this.tel = tel;
		this.email = email;
		this.userPhotoUrl = userPhotoUrl;
		this.follow = follow;
		this.follower = follower;
		this.bookCount = bookCount;
		this.bookList = bookList;
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

	public ArrayList<BookData> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<BookData> bookList) {
		this.bookList = bookList;
	}
    
    
    
    
	
}
