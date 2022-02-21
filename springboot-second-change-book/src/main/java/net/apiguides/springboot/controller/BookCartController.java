package net.apiguides.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.el.StaticFieldELResolver;
import javax.print.attribute.PrintRequestAttribute;

import org.apache.catalina.connector.Response;
import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;

import org.springframework.http.*;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import net.apiguides.springboot.entity.BookCartData;
import net.apiguides.springboot.entity.CheckOutData;
import net.apiguides.springboot.entity.CheckOutProductList;
import net.apiguides.springboot.entity.CheckOutSaveData;
import net.apiguides.springboot.entity.OrderData;
import net.apiguides.springboot.entity.OrderSaveData;
import net.apiguides.springboot.entity.ResponseData;
import net.apiguides.springboot.entity.SearchData;
import net.apiguides.springboot.entity.UserData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.BookCartRepository;
import net.apiguides.springboot.repository.CheckOutDataRepository;
import net.apiguides.springboot.repository.CheckOutListRepository;
import net.apiguides.springboot.repository.OrderDataRepository;

@RestController
@RequestMapping("/api/books")
public class BookCartController {

	private static final int TRADING = 0;
	
	private static final int TRAD_COMPETELY = 1;
	
	private static final int BUYER = 555;
	
	private static final int SELLER = 666;
	
	private static final int RESULT_OK = 200;
	
	private static final int RESULT_ERROR = 404;
	
	@Autowired
	private BookCartRepository bookCartRepository;
	
	@Autowired
	private OrderDataRepository orderDataRepository;
	
	@Autowired
	private CheckOutDataRepository checkOutDataRepository;
	
	@Autowired
	private CheckOutListRepository checkOutListRepository;
	
	@PostMapping("/changeOrderStatus")
	public ResponseData changeOrderStatus(@RequestBody OrderData orderData) {
		
		
		if (orderData == null || orderData.getOrderId() == null) {
			
			return getJson(RESULT_ERROR, "Error something is null");
		}
		
		boolean isFoundData = false;
		
		for(OrderData data : orderDataRepository.findAll()) {
			
			if (data.getOrderId().equals(orderData.getOrderId())) {
				
				data.setStatus(TRAD_COMPETELY);
				orderDataRepository.save(data);
				isFoundData = true;
			
			}
			
		}
		
		if (!isFoundData) {
			return getJson(RESULT_ERROR, "Not found this orderId");
		}
		
		return getJson(RESULT_OK, "Trading successful!");
		
	}
	
	@PostMapping("/checkOut")
	public ResponseData startToCheckOut(@RequestBody ArrayList<CheckOutSaveData> checkOutList){
		
		String orderId = UUID.randomUUID().toString();
		
		if (checkOutList == null || checkOutList.isEmpty()) {
			
			
			return getJson(RESULT_ERROR, "data is null or empty , try again.");
		}
		
		boolean isFoundError = false;
		
		for(CheckOutSaveData data : checkOutList) {
			
			if (data.getMyUid() == null || data.getUploaderUid() == null) {
				isFoundError = true;
				break;
			}
			
			data.setOrderId(orderId);
			
			CheckOutData checkOutData = new CheckOutData();
			checkOutData.setAllSelected(data.isAllSelected());
			checkOutData.setMyUid(data.getMyUid());
			checkOutData.setOrderId(data.getOrderId());
			checkOutData.setUploaderUid(data.getUploaderUid());
			checkOutData.setUserEmail(data.getUserEmail());
			checkOutData.setShipmentFee(data.getShipmentFee());
			checkOutData.setShipmentWay(data.getShipmentWay());
			
			checkOutDataRepository.save(checkOutData);
			
			for(CheckOutProductList list : data.getProductLists()) {
				if (list.getMyUid() == null || list.getUploaderUid() == null) {
					isFoundError = true;
					break;
				}
				list.setOrderId(orderId);
				checkOutListRepository.save(list);
			}
		}
		
		if (isFoundError) {
			
			return getJson(RESULT_ERROR, "User's uid is not found!");
		}
		
		
		OrderData orderData = new OrderData();
		
		orderData.setMyUid(checkOutList.get(0).getMyUid());
		
		orderData.setStatus(TRADING);
		
		orderData.setRole(BUYER);
		
		orderData.setOrderId(orderId);
		
		createSellerOrder(checkOutList, orderId);
		
		orderDataRepository.save(orderData);
		
		
		for(BookCartData cartData : bookCartRepository.findAll()) {
			if (cartData.getMyUid().equals(orderData.getMyUid())) {
				bookCartRepository.delete(cartData);
			}
		}
		
		
		return getJson(RESULT_OK,"Check out successful!");
	}
	
	
	private ResponseData getJson(int result , String message) {
		
	
		
		return new ResponseData(result,message);
	}


	private void createSellerOrder(ArrayList<CheckOutSaveData> checkOutList,String orderId) {
		
		
		
		for(CheckOutSaveData data : checkOutList) {
			
			OrderData orderData = new OrderData();
			
			orderData.setMyUid(data.getUploaderUid());
			
			orderData.setStatus(TRADING);
			
			orderData.setRole(SELLER);
			
			orderData.setOrderId(orderId);
			
			orderDataRepository.save(orderData);
		}
		
	}
	
	
	@PostMapping("/getAllOrderList")
	public List<OrderSaveData> getOrderList(@RequestBody UserData userData){
		
		List<OrderData> orderList = orderDataRepository.findAll();
		
		List<CheckOutData> checkOutDataList = checkOutDataRepository.findAll();
		
		List<CheckOutProductList> checkOutProductLists = checkOutListRepository.findAll();
		
		
		List<OrderSaveData> finalList = new ArrayList<>();
		
		for(OrderData data : orderList) {
			
			//賣家的訂單
			if (data.getMyUid().equals(userData.getUid()) && data.getRole() == SELLER) {
				setSellerOrderList(checkOutDataList,finalList,data,checkOutProductLists , data.getMyUid());
				
				continue;
			}
			
			//買家的訂單
			if (data.getMyUid().equals(userData.getUid()) && data.getRole() == BUYER) {
				setBuyerOrderList(checkOutDataList,finalList,data,checkOutProductLists);
			}
			
			
		}
		
		return finalList;
	
	}
	
	private void setSellerOrderList(List<CheckOutData> checkOutDataList, List<OrderSaveData> finalList, OrderData data
			, List<CheckOutProductList> checkOutProductLists , String uid){
		ArrayList<CheckOutSaveData> checkOutDatas = new ArrayList<>();
		
		for(CheckOutData checkOutData : checkOutDataList) {
			
			if (checkOutData.getOrderId().equals(data.getOrderId()) && checkOutData.getUploaderUid().equals(uid)) {
				
				CheckOutSaveData saveData = new CheckOutSaveData();
				saveData.setId(checkOutData.getId());
				saveData.setAllSelected(checkOutData.isAllSelected());
				saveData.setMyUid(checkOutData.getMyUid());
				saveData.setUploaderUid(checkOutData.getUploaderUid());
				saveData.setUserEmail(checkOutData.getUserEmail());
				saveData.setOrderId(checkOutData.getOrderId());
				saveData.setShipmentFee(checkOutData.getShipmentFee());
				saveData.setShipmentWay(checkOutData.getShipmentWay());
				
				checkOutDatas.add(saveData);
				
				ArrayList<CheckOutProductList> productLists = new ArrayList<>();
				for(CheckOutProductList list : checkOutProductLists) {
					
					if (list.getOrderId().equals(checkOutData.getOrderId()) && list.getUploaderUid().equals(uid)) {
						productLists.add(list);
					}
					
				}
				saveData.setProductLists(productLists);
			}
			
		}
		
		OrderSaveData saveData = new OrderSaveData();
		saveData.setId(data.getId());
		saveData.setMyUid(data.getMyUid());
		saveData.setOrderId(data.getOrderId());
		saveData.setStatus(data.getStatus());
		saveData.setRole(data.getRole());
		saveData.setCheckOutList(checkOutDatas);
		
		finalList.add(saveData);
	}
	
	
	private void setBuyerOrderList(List<CheckOutData> checkOutDataList, List<OrderSaveData> finalList, OrderData data
			, List<CheckOutProductList> checkOutProductLists){
		ArrayList<CheckOutSaveData> checkOutDatas = new ArrayList<>();
		
		for(CheckOutData checkOutData : checkOutDataList) {
			if (checkOutData.getOrderId().equals(data.getOrderId())) {
				
				CheckOutSaveData saveData = new CheckOutSaveData();
				saveData.setId(checkOutData.getId());
				saveData.setAllSelected(checkOutData.isAllSelected());
				saveData.setMyUid(checkOutData.getMyUid());
				saveData.setUploaderUid(checkOutData.getUploaderUid());
				saveData.setUserEmail(checkOutData.getUserEmail());
				saveData.setOrderId(checkOutData.getOrderId());
				saveData.setShipmentFee(checkOutData.getShipmentFee());
				saveData.setShipmentWay(checkOutData.getShipmentWay());
				checkOutDatas.add(saveData);
				
				ArrayList<CheckOutProductList> productLists = new ArrayList<>();
				for(CheckOutProductList list : checkOutProductLists) {
					
					if (list.getOrderId().equals(checkOutData.getOrderId())) {
						productLists.add(list);
					}
					
				}
				saveData.setProductLists(productLists);
			}
			
		}
		
		OrderSaveData saveData = new OrderSaveData();
		saveData.setId(data.getId());
		saveData.setMyUid(data.getMyUid());
		saveData.setRole(data.getRole());
		saveData.setOrderId(data.getOrderId());
		saveData.setStatus(data.getStatus());
		saveData.setCheckOutList(checkOutDatas);
		
		finalList.add(saveData);
	}
	
	
	//get all BookDatas ;
	@PostMapping("/allCartList")
	public List<BookCartData> getAllBookCartDatas(@RequestBody UserData userData){
		
		System.out.println("uid : "+userData.getUid());
		
		//get all BookDatas
		List<BookCartData> allBookCartList = bookCartRepository.findAll();
		
		List<BookCartData> userBookCartList = new ArrayList<BookCartData>();
		
		
		
		for(BookCartData data : allBookCartList) {
			System.out.println(" dataBase uid : "+data.getUploaderUid());
			
			if(data.getMyUid().equals(userData.getUid())) {
				userBookCartList.add(data);
			}
		}
		

		
		return userBookCartList;
		
	}
	

	
	
	
	//create BookCartData
	@PostMapping("/addCart")
	public BookCartData addBookCartData(@RequestBody BookCartData bookCartData) {
		
		List<BookCartData> list = bookCartRepository.findAll();
		
		boolean isFoundData = false;
		BookCartData newData = null;
		for(BookCartData data : list) {
			if (data.getBookName().equals(bookCartData.getBookName()) && data.getMyUid().equals(bookCartData.getMyUid())) {
				int qty = Integer.parseInt(data.getQty().trim()) + Integer.parseInt(bookCartData.getQty().trim());
				newData = data;
				newData.setQty(qty+"");
				isFoundData = true;
				break;
			}
		}
		
		if (isFoundData) {
			
			return this.bookCartRepository.save(newData);
		}
		
		return bookCartRepository.save(bookCartData);
	}
	
	
	@PostMapping("/updateCart")
	public BookCartData editBookCartData(@RequestBody BookCartData bookData) {
		
		BookCartData oldBookData = this.bookCartRepository.findById(bookData.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		
		oldBookData = bookData;
		
		return this.bookCartRepository.save(oldBookData);
		
	}

	
	//delete BookData by id
	@PostMapping("/deleteCart")
	public BookCartData deleteBookCartData(@RequestBody BookCartData bookCartData){
		
		BookCartData oldBookData = this.bookCartRepository.findById(bookCartData.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		this.bookCartRepository.delete(oldBookData);
		return oldBookData;
	}

	
}
