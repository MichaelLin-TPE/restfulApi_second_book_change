package net.apiguides.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.apiguides.springboot.entity.BookData;
import net.apiguides.springboot.entity.MessageData;
import net.apiguides.springboot.entity.ResponseData;
import net.apiguides.springboot.repository.MessageRepository;



@RestController
@RequestMapping("/api/books")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	
	@PostMapping("/getAllMsgList")
	public List<MessageData> getMessageList(@RequestBody BookData bookData){
		
		if (bookData.getUploaderUid() == null || bookData.getBookName() == null) {
			
			return new ArrayList<>();
		}
		
		List<MessageData> allMessageList = messageRepository.findAll();
		
		List<MessageData> msgList = new ArrayList<>();
		
		for(MessageData data : allMessageList) {
			if (data.getUploaderUid().equals(bookData.getUploaderUid()) && data.getBookName().equals(bookData.getBookName()) && data.getBookId() == bookData.getId()) {
				msgList.add(data);
			}
		}
		
		return msgList;
	}
	
	
	@PostMapping("/addMessage")
	public ResponseData addMsgData(@RequestBody MessageData messageData) {
		
		if (messageData.getUploaderUid() == null || messageData.getBookName() == null || messageData.getMsg() == null || messageData.getUidForLeftMsg() == null) {
			
			return getJson(404, "Data is missing some variable");
		}
		
		messageRepository.save(messageData);
		
		return getJson(200, "Add message successful!");
		
	}
	
	@PostMapping("/editMessage")
	public ResponseData editMessage(@RequestBody MessageData messageData) {
		
		boolean isFoundData = false;
		
		for(MessageData data : messageRepository.findAll()) {
			
			if (data.getId() == messageData.getId()) {
				isFoundData = true;
			}
			
		}
		
		if (!isFoundData) {
			
			return getJson(404, "Can not find this data!");
		}
		
		messageRepository.save(messageData);
		
		return getJson(200, "EditMessage successful");
	}
	
	
	@PostMapping("/deleteMessage")
	public ResponseData deleteMessage(@RequestBody MessageData messageData) {
		
		boolean isFoundData = false;
		
		for(MessageData data : messageRepository.findAll()) {
			
			if (data.getId() == messageData.getId()) {
				isFoundData = true;
			}
			
		}
		
		if (!isFoundData) {
			
			return getJson(404, "Can not find this data!");
		}
		
		messageRepository.delete(messageData);
		
		return getJson(200, "EditMessage successful");
		
		
	}
	
	private ResponseData getJson(int result , String msg) {
		
		return new ResponseData(result,msg);
	}
	
}
