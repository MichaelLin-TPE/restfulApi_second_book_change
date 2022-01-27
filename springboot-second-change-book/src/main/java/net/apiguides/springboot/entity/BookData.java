package net.apiguides.springboot.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class BookData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "book_name")
    private String bookName;
	@Column(name = "classify")
    private String classify;
	@Column(name = "description")
    private String description;
	@Column(name = "qty")
	private String qty;
	@Column(name = "status")
    private String status;
	@Column(name = "shipment")
    private String shipment;
	@Column(name = "remark")
    private String remark;
	@Column(name = "unitPrice")
    private String unitPrice;
	@Column(name = "totalPrice")
    private String totalPrice;
	@Column(name = "uid")
    private String uid;
	@Column(name = "time")
    private long time;
	@Column(name = "photoUrl")
    private String photoUrl;
	@Column(name = "userEmail")
    private String userEmail;
	
	public BookData() {
		
	}
	
	

	public BookData(String bookName, String classify, String description, String qty, String status, String shipment,
			String remark, String unitPrice, String totalPrice, String uid, long time, String photoUrl,
			String userEmail) {
		super();
		this.bookName = bookName;
		this.classify = classify;
		this.description = description;
		this.qty = qty;
		this.status = status;
		this.shipment = shipment;
		this.remark = remark;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.uid = uid;
		this.time = time;
		this.photoUrl = photoUrl;
		this.userEmail = userEmail;
	}



	public String getClassify() {
		return classify;
	}



	public void setClassify(String classify) {
		this.classify = classify;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getQty() {
		return qty;
	}



	public void setQty(String qty) {
		this.qty = qty;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getShipment() {
		return shipment;
	}



	public void setShipment(String shipment) {
		this.shipment = shipment;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}



	public String getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public long getTime() {
		return time;
	}



	public void setTime(long time) {
		this.time = time;
	}



	public String getPhotoUrl() {
		return photoUrl;
	}



	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
	
	
	
	
}
