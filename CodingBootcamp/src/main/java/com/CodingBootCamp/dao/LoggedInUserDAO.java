package com.CodingBootCamp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingBootCamp.model.LoggedInUsers;

public interface LoggedInUserDAO  extends JpaRepository<LoggedInUsers, Long>{

public LoggedInUsers findByEmail(String email);
	
	//@Query("SELECT u FROM User u WHERE u.event_id = ?1")
	public List<LoggedInUsers> findByEventId(Long id);
	public List<LoggedInUsers> findByEventIdAndEmail(Long id,String email);
	
	
}
