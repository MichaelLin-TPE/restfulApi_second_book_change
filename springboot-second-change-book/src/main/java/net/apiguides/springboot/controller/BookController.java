package net.apiguides.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import org.springframework.http.*;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import net.apiguides.springboot.entity.BookCartData;
import net.apiguides.springboot.entity.BookData;
import net.apiguides.springboot.entity.FavoriteData;
import net.apiguides.springboot.entity.SearchData;
import net.apiguides.springboot.entity.UserData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.BookCartRepository;
import net.apiguides.springboot.repository.BookRepository;
import net.apiguides.springboot.repository.FavoriteRepository;



@RestController
@RequestMapping("/api/books")
public class BookController {
	
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired 
	private BookCartRepository cartRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	
	//get all BookDatas ;
	@PostMapping("/allList")
	public List<BookData> getAllBookDatas(@RequestBody UserData userData){
		
		//get all BookDatas
		List<BookData> allList = bookRepository.findAll();
		
		List<FavoriteData> favList = favoriteRepository.findAll();
		
		System.out.println("favList size "+favList.size());
		
		for(BookData data : allList) {
			data.setSelectHeart(false);
		}
		
		for(BookData data : allList) {
			
			for(FavoriteData favoriteData : favoriteRepository.findAll()) {
				System.out.println("book : "+data.getBookName()+" , fav book : "+favoriteData.getBookName() + " , fav uid : "+favoriteData.getMyUid()+" , user uid : "+userData.getUid());
				if (data.getBookName().equals(favoriteData.getBookName()) && favoriteData.getMyUid().equals(userData.getUid())) {
					System.out.println("find favorite");
					data.setSelectHeart(true);
					break;
				}	
			}
		}
		
		
		System.out.println(new Gson().toJson(allList));
		
		return allList;
		
	}
	
	
	
	
	
	//get BookData by id
	@PostMapping("/searchBookByBookName")
	public List<BookData> getBookDataByBookName(@RequestBody SearchData searchData) {
		
		List<BookData> allList = this.bookRepository.findAll();
		
		ArrayList<BookData> searchList = new ArrayList<>();
		
		for(BookData data : allList) {
			
			if (data.getBookName().contains(searchData.getName()) ) {
				searchList.add(data);
				continue;
			}			
			if (data.getClassify().contains(searchData.getName())) {
				searchList.add(data);
			}
			
		}
		
		
		for(BookData data : searchList) {
			
			for(FavoriteData favoriteData : favoriteRepository.findAll()) {
				if (data.getBookName().equals(favoriteData.getBookName()) && favoriteData.getMyUid().equals(searchData.getUid())) {
					data.setSelectHeart(true);
				}
			}
			
		}
		
		
		return searchList;
	}
	
	//create BookData
	@PostMapping("/addBook")
	public BookData addBookData(@RequestBody BookData bookData) {
		
		System.out.println("book Data "+new Gson().toJson(bookData));
		
		return this.bookRepository.save(bookData);
	}
	
	
	@PostMapping("/editBookData")
	public BookData editBookData(@RequestBody BookData bookData) {
		
		BookData oldBookData = this.bookRepository.findById(bookData.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		
		oldBookData = bookData;
		
		for(BookCartData cartData : cartRepository.findAll()) {
			
			if (cartData.getBookName().equals(oldBookData.getBookName()) && cartData.getUploaderUid().equals(oldBookData.getUploaderUid())) {
				System.out.println("修改購物車資訊");
				cartData.setClassify(oldBookData.getClassify());
				cartData.setDescription(oldBookData.getDescription());
				cartData.setPhotoUrl(oldBookData.getPhotoUrl());
				cartData.setRemark(oldBookData.getRemark());
				cartData.setShipment(oldBookData.getShipment());
				cartData.setStatus(oldBookData.getStatus());
				cartData.setUnitPrice(oldBookData.getUnitPrice());
				cartData.setTotalPrice(oldBookData.getTotalPrice());
				cartRepository.save(cartData);
			}
		}
		
		for(FavoriteData favoriteData : favoriteRepository.findAll()) {
			
			if (favoriteData.getBookName().equals(oldBookData.getBookName()) && favoriteData.getUploaderUid().equals(oldBookData.getUploaderUid())) {
				
				favoriteData.setClassify(oldBookData.getClassify());
				favoriteData.setDescription(oldBookData.getDescription());
				favoriteData.setPhotoUrl(oldBookData.getPhotoUrl());
				favoriteData.setQty(oldBookData.getQty());
				favoriteData.setRemark(oldBookData.getRemark());
				favoriteData.setShipment(oldBookData.getShipment());
				favoriteData.setStatus(oldBookData.getStatus());
				favoriteData.setUnitPrice(oldBookData.getUnitPrice());
				favoriteData.setTotalPrice(oldBookData.getTotalPrice());
				favoriteRepository.save(favoriteData);
			}
		}
		
		
		return this.bookRepository.save(oldBookData);
		
	}
	
	
	//update BookData
	@PutMapping("/{id}")
	public BookData updateBookData(@RequestBody BookData BookData , @PathVariable(value = "id")long BookDataId) {
		
		BookData oldBookData = this.bookRepository.findById(BookDataId)
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		oldBookData.setBookName(BookData.getBookName());
		
		return this.bookRepository.save(oldBookData);
	}
	
	
	//delete BookData by id
	@DeleteMapping("/{id}")
	public ResponseEntity<BookData> deleteBookData(@PathVariable("id")long BookDataId){
		
		BookData oldBookData = this.bookRepository.findById(BookDataId)
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		this.bookRepository.delete(oldBookData);
		return ResponseEntity.ok().build();
	}

	

}
