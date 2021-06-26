package com.CodingBootCamp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.CodingBootCamp.model.User;


public interface UserDAO extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	//@Query("SELECT u FROM User u WHERE u.event_id = ?1")
	public List<User> findByEventId(Long id);
	public List<User> findByEventIdAndEmail(Long id,String email);
	
	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
	public User findByVerificationCode(String code);
}
