package net.apiguides.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class MessageData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "uid_for_left_msg")
	private String uidForLeftMsg;
	
	@Column(name = "msg")
	private String msg;
	
	@Column(name = "bookName")
	private String bookName;
	
	@Column(name = "uploader_uid")
	private String uploaderUid;
	
	@Column(name = "book_id")
	private long bookId;
	
	public MessageData() {
		
	}

	public MessageData(String uidForLeftMsg, String msg,String bookName,String uploaderUid,long bookId) {
		super();
		this.uidForLeftMsg = uidForLeftMsg;
		this.msg = msg;
		this.bookName = bookName;
		this.uploaderUid = uploaderUid;
		this.bookId = bookId;
	}
	
	

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUploaderUid() {
		return uploaderUid;
	}

	public void setUploaderUid(String uploaderUid) {
		this.uploaderUid = uploaderUid;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUidForLeftMsg() {
		return uidForLeftMsg;
	}

	public void setUidForLeftMsg(String uidForLeftMsg) {
		this.uidForLeftMsg = uidForLeftMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
	
}
