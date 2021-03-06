package net.apiguides.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import net.apiguides.springboot.entity.BookData;
import net.apiguides.springboot.entity.FavoriteData;
import net.apiguides.springboot.entity.UserAllInformation;
import net.apiguides.springboot.entity.UserBasicData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.BookRepository;
import net.apiguides.springboot.repository.FavoriteRepository;
import net.apiguides.springboot.repository.UserBasicDataRepository;
import net.apiguides.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/books")
public class UserBasicDataController {

	
	@Autowired
	private UserBasicDataRepository userBasicDataRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	
	
	@PostMapping("/getAllSellerData")
	public List<UserBasicData> getSellerData(@RequestBody List<UserBasicData> sellerList){
		
		List<UserBasicData> allUserList = userBasicDataRepository.findAll();
		
		List<UserBasicData> sellerAllList = new ArrayList<>();
		
		for(UserBasicData data : allUserList) {
			
			for(UserBasicData userBasicData : sellerList) {
				
				if (data.getUserUid().equals(userBasicData.getUserUid())) {
					sellerAllList.add(data);
				}
				
			}
			
		}
		
		return sellerAllList;
	}
	
	
	@PostMapping("/checkBasicData")
	public UserBasicData checkUserBasicExit(@RequestBody UserBasicData userBasicData) {
		
		List<UserBasicData> allUserList = userBasicDataRepository.findAll();
		
		boolean isFoundSameUser = false;
		
		for(UserBasicData data : allUserList) {
			if (data.getUserUid() == null) {
				continue;
			}
			if (data.getUserUid().equals(userBasicData.getUserUid())) {
				userBasicData = data;
				isFoundSameUser = true;
				break;
			}
		}
		if (isFoundSameUser) {
			return userBasicData;
		}
		
		return this.userBasicDataRepository.save(userBasicData);
	}
	
	
	@PostMapping("/editUserData")
	public UserBasicData editBasicData(@RequestBody UserBasicData userBasicData) {
		
		for(UserBasicData data : userBasicDataRepository.findAll()) {
			
			if (data.getUserUid().equals(userBasicData.getUserUid())) {
				userBasicData.setId(data.getId());
			}	
		}
		
		UserBasicData olBasicData = this.userBasicDataRepository.findById(userBasicData.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		
		olBasicData = userBasicData;
		return this.userBasicDataRepository.save(olBasicData);
	}
	
	@PostMapping("/searchUserAllInformation")
	public UserAllInformation searchUserAllInformation(@RequestBody UserBasicData userBasicData) {
		
		
		UserAllInformation information = new UserAllInformation();
		
		
		for(UserBasicData data : userBasicDataRepository.findAll()) {
			
			if (data.getUserUid().equals(userBasicData.getUserUid())) {
				
				information.setAccount(data.getAccount() == null ? "" : data.getAccount());
				information.setBookCount(data.getBookCount());
				information.setEmail(data.getEmail() == null ? "" : data.getEmail());
				information.setFollow(data.getFollow());
				information.setFollower(data.getFollower());
				information.setNickName(data.getNickName() == null ? "" : data.getNickName());
				information.setTel(data.getTel() == null ? "" : data.getTel());
				information.setUserPhotoUrl(data.getUserPhotoUrl() == null ? "" : data.getUserPhotoUrl());
				information.setUserUid(data.getUserUid() == null ? "" : data.getUserUid());
				information.setBankCode(data.getBankCode() == null ? "" : data.getBankCode());
				information.setBankAccount(data.getBankAccount() == null ? "" : data.getBankAccount());
				information.setBankName(data.getBankName() == null ? "" : data.getBankName());
				
				break;
			}
		}
		
		ArrayList<BookData> bookList = new ArrayList<>();
		
		for(BookData bookData : bookRepository.findAll()) {
			if (bookData.getUploaderUid().equals(userBasicData.getUserUid())) {
				bookList.add(bookData);
			}
		}
		
		information.setBookList(bookList);
		
		for(BookData data : information.getBookList()) {
			
			for(FavoriteData favoriteData : favoriteRepository.findAll()) {
				
				if (favoriteData.getMyUid().equals(userBasicData.getUserUid()) && data.getBookName().equals(favoriteData.getBookName())) {
					
					data.setSelectHeart(true);
					
				}
				
			}
			
		}
		
		return information;
		
	}
	
	@PostMapping("/searchOtherUserAllInformation")
	public UserAllInformation searchOtherUserAllInformation(@RequestBody UserBasicData userBasicData) {
		
		
		UserAllInformation information = new UserAllInformation();
		
		
		for(UserBasicData data : userBasicDataRepository.findAll()) {
			
			if (data.getUserUid().equals(userBasicData.getUserUid())) {
				
				information.setAccount(data.getAccount() == null ? "" : data.getAccount());
				information.setBookCount(data.getBookCount());
				information.setEmail(data.getEmail() == null ? "" : data.getEmail());
				information.setFollow(data.getFollow());
				information.setFollower(data.getFollower());
				information.setNickName(data.getNickName() == null ? "" : data.getNickName());
				information.setTel(data.getTel() == null ? "" : data.getTel());
				information.setUserPhotoUrl(data.getUserPhotoUrl() == null ? "" : data.getUserPhotoUrl());
				information.setUserUid(data.getUserUid() == null ? "" : data.getUserUid());
				information.setBankCode(data.getBankCode() == null ? "" : data.getBankCode());
				information.setBankAccount(data.getBankAccount() == null ? "" : data.getBankAccount());
				information.setBankName(data.getBankName() == null ? "" : data.getBankName());
				
				break;
			}
		}
		
		ArrayList<BookData> bookList = new ArrayList<>();
		
		for(BookData bookData : bookRepository.findAll()) {
			if (bookData.getUploaderUid().equals(userBasicData.getUserUid())) {
				bookList.add(bookData);
			}
		}
		
		information.setBookList(bookList);
		
		for(BookData data : information.getBookList()) {
			
			for(FavoriteData favoriteData : favoriteRepository.findAll()) {
				
				if (favoriteData.getMyUid().equals(userBasicData.getEmail()) && data.getBookName().equals(favoriteData.getBookName())) {
					
					data.setSelectHeart(true);
					
				}
				
			}
			
		}
		
		return information;
		
	}
	
	@PostMapping("/deleteUser")
	public UserBasicData deleteUserBasicData(@RequestBody UserBasicData data) {
		
		UserBasicData basicData = this.userBasicDataRepository.findById(data.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		
		this.userBasicDataRepository.delete(basicData);
		return data;
	}
	
	
	
}
