package net.apiguides.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "check_out_list")
public class CheckOutProductList implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "qty")
    private String qty;
    
	@Column(name = "unit_price")
    private String unitPrice;
    
	@Column(name = "total_price")
    private String totalPrice;
    
	@Column(name = "photo_url")
    private String photoUrl;
    
	@Column(name = "user_email")
    private String userEmail;
    
	@Column(name = "uploader_uid")
    private String uploaderUid;
    
	@Column(name = "myUid")
    private String myUid;
    
	@Column(name = "is_selected_product")
    private boolean isSelectedProduct;
	
	@Column(name = "order_id")
	private String orderId;

    public CheckOutProductList(String bookName, String qty, String unitPrice, String totalPrice, String photoUrl
    		, String userEmail, long id, String uploaderUid, String myUid, boolean isSelectedProduct
    		, String orderId) {
        this.bookName = bookName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.photoUrl = photoUrl;
        this.userEmail = userEmail;
        this.id = id;
        this.uploaderUid = uploaderUid;
        this.myUid = myUid;
        this.isSelectedProduct = isSelectedProduct;
        this.orderId = orderId;
    }
    
    

    public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public CheckOutProductList() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
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

    public String getUploaderUid() {
        return uploaderUid;
    }

    public void setUploaderUid(String uploaderUid) {
        this.uploaderUid = uploaderUid;
    }

    public String getMyUid() {
        return myUid;
    }

    public void setMyUid(String myUid) {
        this.myUid = myUid;
    }

    public boolean isSelectedProduct() {
        return isSelectedProduct;
    }

    public void setSelectedProduct(boolean selectedProduct) {
        isSelectedProduct = selectedProduct;
    }

}
