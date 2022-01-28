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
			System.out.println(" dataBase uid : "+data.getUid());
			
			if(data.getUid().equals(userData.getUid())) {
				userBookCartList.add(data);
			}
		}
		
		
		return userBookCartList;
		
	}
	

	
	
	
	//create BookCartData
	@PostMapping("/addCart")
	public BookCartData addBookCartData(@RequestBody BookCartData bookCartData) {
		return this.bookCartRepository.save(bookCartData);
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
	public String deleteBookCartData(@RequestBody BookCartData bookCartData){
		
		BookCartData oldBookData = this.bookCartRepository.findById(bookCartData.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		this.bookCartRepository.delete(oldBookData);
		return bookCartData.getBookName() + " Delete Success";
	}

	
}
