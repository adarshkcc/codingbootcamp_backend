package com.CodingBootCamp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingBootCamp.model.LoggedInUsers;
import com.CodingBootCamp.model.User;
import com.CodingBootCamp.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class RegistrationAndLoginController {

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(RegistrationAndLoginController.class);

	@PostMapping("/process_register/{event_id}")
	public String processRegister( @PathVariable("event_id") Long event_id,   @RequestBody User user, HttpServletRequest request) {
		logger.info("user registered");
		System.out.println(getSiteURL(request));
		return userService.register(user, getSiteURL(request),event_id);

	}

	public String getSiteURL(HttpServletRequest request) {
		logger.info("site url converted to string");
		String siteURL = request.getRequestURL().toString();
		System.out.println(siteURL.replace(request.getServletPath(), ""));
		return siteURL.replace(request.getServletPath(), "");

	}
	@GetMapping("/registered_users/{meeting_id}")
	public List<User> getById(@PathVariable("meeting_id") Long meeting_id) {
		return userService.getUserByMId(meeting_id);
	}

	@GetMapping("/registered_users")
	public List<User> getAllRegisteredUsers() {
		return userService.getRegisteredUsers();
	}

	@PostMapping("/loginUser/{meeting_id}")
	public String login(@RequestBody User user, @PathVariable("meeting_id") Long meeting_id) {
		logger.info("user logged in");
		return userService.login(user, meeting_id);
	}

	@PostMapping("/email/{mid}")
	public String emailExist(@RequestBody User user, @PathVariable("mid") Long mid) {
		logger.info("Email Exists");

		return userService.emailExist(user, mid);
	}

	
	@GetMapping("/loggedin_users")
	public List<LoggedInUsers> getLoggedInUser() {
		return userService.getLoggedInUser();
	}

	@PutMapping("/submitFeedback/{meeting_id}")
	public void submitFeedback(@PathVariable("meeting_id") Long meeting_id,@RequestBody LoggedInUsers user) {
		userService.submitFeedback(user,meeting_id);    
	}
	@GetMapping("/feedback")
	public List<LoggedInUsers> getFeedback() {
		return userService.getFeedbacks();
	}
}
