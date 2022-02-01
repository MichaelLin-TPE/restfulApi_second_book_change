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
import net.apiguides.springboot.entity.UserAllInformation;
import net.apiguides.springboot.entity.UserBasicData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.BookRepository;
import net.apiguides.springboot.repository.UserBasicDataRepository;

@RestController
@RequestMapping("/api/books")
public class UserBasicDataController {

	
	@Autowired
	private UserBasicDataRepository userBasicDataRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@PostMapping("/checkBasicData")
	public UserBasicData checkUserBasicExit(UserBasicData userBasicData) {
		
		List<UserBasicData> allUserList = userBasicDataRepository.findAll();
		
		boolean isFoundSameUser = false;
		
		for(UserBasicData data : allUserList) {
			if (data.getUserUid().equals(userBasicData.getUserUid())) {
				isFoundSameUser = true;
				break;
			}
		}
		if (isFoundSameUser) {
			return userBasicData;
		}
		
		this.userBasicDataRepository.save(userBasicData);
		
		
		return userBasicData;
	}
	
	
	@PostMapping("/editUserData")
	public UserBasicData editBasicData(UserBasicData userBasicData) {
		
		UserBasicData olBasicData = this.userBasicDataRepository.findById(userBasicData.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		
		olBasicData = userBasicData;
		return this.userBasicDataRepository.save(olBasicData);
	}
	
	@PostMapping("/searchUserAllInformation")
	public UserAllInformation searchUserAllInformation(UserBasicData userBasicData) {
		
		
		UserAllInformation information = new UserAllInformation();
		
		
		for(UserBasicData data : userBasicDataRepository.findAll()) {
			
			if (data.getUserUid().equals(userBasicData.getUserUid())) {
				information.setAccount(data.getAccount());
				information.setBookCount(data.getBookCount());
				information.setEmail(data.getEmail());
				information.setFollow(data.getFollow());
				information.setFollower(data.getFollower());
				information.setNickName(data.getNickName());
				information.setTel(data.getTel());
				information.setUserPhotoUrl(data.getUserPhotoUrl());
				information.setUserUid(data.getUserUid());
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
		
		return information;
		
	}
	
	@PostMapping("/deleteUser")
	public UserBasicData deleteUserBasicData(UserBasicData data) {
		
		UserBasicData basicData = this.userBasicDataRepository.findById(data.getId())
				.orElseThrow(() -> new ResourceNotFoundException("BookData not found ID"));
		
		this.userBasicDataRepository.delete(basicData);
		return data;
	}
	
	
	
}
