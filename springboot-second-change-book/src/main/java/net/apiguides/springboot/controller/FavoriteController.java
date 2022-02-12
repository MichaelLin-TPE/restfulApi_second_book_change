package net.apiguides.springboot.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.apiguides.springboot.entity.FavoriteData;
import net.apiguides.springboot.entity.UserData;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.FavoriteRepository;

@RestController
@RequestMapping("/api/books")
public class FavoriteController {

	@Autowired
	private FavoriteRepository favoriteRepository;
	
	
	
	@PostMapping("/getAllFavorite")
	public List<FavoriteData> getFavoriteList(@RequestBody UserData userData){
		
		List<FavoriteData> favoriteDatas = favoriteRepository.findAll();
		
		
		ArrayList<FavoriteData> myList = new ArrayList<>();
		
		for(FavoriteData data : favoriteDatas) {
			if (data.getMyUid().equals(userData.getUid())) {
				myList.add(data);
			}
		}
		
		return myList;
		
	}
	
	@PostMapping("/addFavorite")
	public FavoriteData addFavoriteData(@RequestBody FavoriteData favoriteData) {
		
		
		boolean isFoundSameData = false;
		
		for(FavoriteData data : favoriteRepository.findAll()) {
			
			if (data.getBookName().equals(favoriteData.getBookName()) && data.getMyUid().equals(favoriteData.getMyUid())) {
				isFoundSameData = true;
			}
		}
		
		if (isFoundSameData) {
			
			favoriteData.setBookName("Do not repeat!");
			return favoriteData;
		}
		
		
		
		return favoriteRepository.save(favoriteData);
		
		
	}
	
	
	@PostMapping("/deleteFavorite")
	public FavoriteData deleteFavoriteData(@RequestBody FavoriteData favoriteData) {
		
		
		for(FavoriteData data : favoriteRepository.findAll()) {
			if (data.getBookName().equals(favoriteData.getBookName()) && data.getMyUid().equals(favoriteData.getMyUid())) {
				favoriteRepository.delete(data);
				break;
			}
		}
		
		
		return favoriteData;
		
		
	}
}
