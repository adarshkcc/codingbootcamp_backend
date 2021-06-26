package com.CodingBootCamp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingBootCamp.model.EmailTemplate;
import com.CodingBootCamp.service.EmailService;



@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/users")
public class EmailController {
	@Autowired 
	private EmailService emailservice;
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	
	 
	 
	 @PostMapping("/send/{meeting_id}")
	 public ResponseEntity<HttpStatus> sends(@RequestBody EmailTemplate email,@PathVariable("meeting_id") String meeting_id){
		 logger.info("Email Sent");
			 if(emailservice.sendEmail(email,Long.parseLong(meeting_id)))
			 return ResponseEntity.status(HttpStatus.OK).build();
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			 
		 
		 
	 }
		
	
	

}