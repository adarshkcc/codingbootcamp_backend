package com.CodingBootCamp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CodingBootCamp.model.Document;


@Repository
public interface DocumentDAO extends JpaRepository<Document, Long> {
	
	@Query("SELECT new Document(d.id,d.name,d.size)FROM Document d ")
	List<Document> findAll();
	@Query("SELECT d FROM Document d WHERE d.meeting_id = ?1")
	public List<Document> findByMeetingId(Long id);
}