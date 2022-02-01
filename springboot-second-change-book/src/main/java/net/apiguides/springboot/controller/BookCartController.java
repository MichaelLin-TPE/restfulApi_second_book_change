package net.apiguides.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import net.apiguides.springboot.entity.BookCartData;
import net.apiguides.springboot.entity.SearchData;
import net.apiguides.springboot.entity.UserData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.BookCartRepository;

@RestController
@RequestMapping("/api/books")
public class BookCartController {

	
	@Autowired
	private BookCartRepository bookCartRepository;
	
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
