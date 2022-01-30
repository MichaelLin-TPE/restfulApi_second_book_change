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

import net.apiguides.springboot.entity.BookData;
import net.apiguides.springboot.entity.SearchData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.BookRepository;



@RestController
@RequestMapping("/api/books")
public class BookController {
	
	
	@Autowired
	private BookRepository bookRepository;
	
	//get all BookDatas ;
	@GetMapping("/allList")
	public List<BookData> getAllBookDatas(){
		
		//get all BookDatas
		List<BookData> allList = bookRepository.findAll();
		
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
