package com.CodingBootCamp.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingBootCamp.model.ScheduleMeeting;
import com.CodingBootCamp.service.ScheduleMeetingService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/meetings")
public class ScheduleMeetingController {

 @Autowired
 private ScheduleMeetingService service;
	
	
	
	
	 @PostMapping("/schedulemeeting")
	 public String send(@RequestBody ScheduleMeeting m){
		 return service.scheduleMeeting(m);
			
		 
	 }
		
	@GetMapping("/get_meetings")
	public  List<ScheduleMeeting>  getMeeting() {
	return service.getMeetingDetails();
	}
	
	@GetMapping("/get_meeting/{meeting_id}")
	public  ScheduleMeeting  getMeetingById( @PathVariable("meeting_id") Long meeting_id) {
		
		try {
			ScheduleMeeting foundEvent=	service.getMeetingById(meeting_id);
			return foundEvent;
		}
		catch(NoSuchElementException e) {
			return null;
		}
		
	 
	}
//	@PostMapping("/modifylink/{name}")
//	public String modifyLink(@PathVariable("name") String name,@RequestBody ScheduleMeeting m) {
//		String link=m.getMeeting_link();
//		return "";
//	}
	
}
