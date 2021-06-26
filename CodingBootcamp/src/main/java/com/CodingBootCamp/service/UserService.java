package com.CodingBootCamp.service;

import java.util.List;

import com.CodingBootCamp.model.LoggedInUsers;
import com.CodingBootCamp.model.User;

public interface UserService {
	public String login(User user,Long meeting_id);
   
    public String emailExist(User user,Long meeting_id);
	public String register(User user, String siteURL, Long meeting_id);

	public List<User> getRegisteredUsers();
	public boolean verify(String verificationCode);
	 public List<User> getUserByMId(Long id);
	 
	 public List<LoggedInUsers> getLoggedInUser();
	 public List<LoggedInUsers> getFeedbacks();
	public void submitFeedback(LoggedInUsers user,Long meeting_id);
	
	
}
