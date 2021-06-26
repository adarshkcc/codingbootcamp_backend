package com.CodingBootCamp.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CodingBootCamp.service.UserService;



@CrossOrigin(origins="http://localhost:3000")
@Controller
@RequestMapping("/users")
public class EmailVerifyController {

	@Autowired
	private UserService service;
	private static final Logger logger = LoggerFactory.getLogger(EmailVerifyController.class);
	
	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		logger.info("Email Verified !!!!! ");
		if (service.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}
}



