package net.apiguides.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.apiguides.springboot.entity.FollowData;
import net.apiguides.springboot.entity.FriendData;
import net.apiguides.springboot.entity.FriendList;
import net.apiguides.springboot.entity.ResponseData;
import net.apiguides.springboot.entity.UserBasicData;
import net.apiguides.springboot.entity.UserData;
import net.apiguides.springboot.repository.FriendRepository;
import net.apiguides.springboot.repository.UserBasicDataRepository;

@RestController
@RequestMapping("/api/books")
public class FriendController {

	
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired
	private UserBasicDataRepository userRepository;
	
	
	@PostMapping("/sendFollow")
	public ResponseData sendFollow(@RequestBody FollowData followData) {
		
		
		if (followData.getMyUid() == null || followData.getTargetUid() == null) {
			
			return getJson(404, "Uid is null !!");
		}
		
		for(UserBasicData userData : userRepository.findAll()) {
			
			if (userData.getUserUid().equals(followData.getMyUid())) {
				userData.setFollow(userData.getFollow() + 1);
				userRepository.save(userData);
				continue;
			}
			if (userData.getUserUid().equals(followData.getTargetUid())) {
				userData.setFollower(userData.getFollower() + 1);
				userRepository.save(userData);
			}
			
		}
		
		createMyUidFriend(followData);
		
		return getJson(200, "Follow successful !!");
		
	}
	
	@PostMapping("/getFollowers")
	public ArrayList<FriendList> getFollowers(@RequestBody UserData userData){
		
		if (userData.getUid() == null) {
			return new ArrayList<>();
		}
		
		ArrayList<FriendList> list = new ArrayList<>();
		
		for(FriendData friendData : friendRepository.findAll()) {
			
			if (friendData.getFriendUid().equals(userData.getUid())) {
				FriendList data = new FriendList();
				data.setUid(friendData.getMyUid());
				for(UserBasicData userBasicData : userRepository.findAll()) {
					if (userBasicData.getUserUid().equals(friendData.getMyUid())) {
						data.setPhotoUrl(userBasicData.getUserPhotoUrl() == null ? "" : userBasicData.getUserPhotoUrl());
						break;
					}
				}
				list.add(data);
			}
		}
		
		return list;
	}
	
	@PostMapping("/getFollow")
	public ArrayList<FriendList> getFollow(@RequestBody UserData userData){
		
		if (userData.getUid() == null) {
			return new ArrayList<>();
		}
		
		ArrayList<FriendList> list = new ArrayList<>();
		
		for(FriendData friendData : friendRepository.findAll()) {
			
			if (friendData.getMyUid().equals(userData.getUid())) {
				FriendList data = new FriendList();
				data.setUid(friendData.getFriendUid());
				data.setPhotoUrl(friendData.getFriendPhotoUrl() == null ? "" : friendData.getFriendPhotoUrl());
				list.add(data);
			}
		}
		
		return list;
	}
	
	
	private void createTargetUidFriend(FollowData followData) {
		
		
		FriendData data = new FriendData();
		data.setMyUid(followData.getTargetUid());
		data.setFriendUid(followData.getMyUid());
		for(UserBasicData basicData : userRepository.findAll()) {
			if (basicData.getUserUid().equals(followData.getMyUid())) {
				data.setFriendPhotoUrl(basicData.getUserPhotoUrl() == null ? "" : basicData.getUserPhotoUrl());
			}
		}
		friendRepository.save(data);
		
	}
	
	private void createMyUidFriend(FollowData followData) {
		
		
		FriendData data = new FriendData();
		data.setMyUid(followData.getMyUid());
		data.setFriendUid(followData.getTargetUid());
		for(UserBasicData basicData : userRepository.findAll()) {
			if (basicData.getUserUid().equals(followData.getTargetUid())) {
				data.setFriendPhotoUrl(basicData.getUserPhotoUrl() == null ? "" : basicData.getUserPhotoUrl());
			}
		}
		friendRepository.save(data);
		
	}
	
	private ResponseData getJson(int result , String message) {
		
	
		
		return new ResponseData(result,message);
	}
	
}
