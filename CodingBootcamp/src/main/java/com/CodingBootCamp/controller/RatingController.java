package com.CodingBootCamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingBootCamp.model.Rating;
import com.CodingBootCamp.service.RatingService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	@PostMapping("/rating")
	public boolean getRating(@RequestBody Rating rating) {
		
		logger.info("rating details");
		ratingService.getRating(rating);
		return true;
	}
	
	@GetMapping("/rating")
	public List<String> findAvgRating() {
		logger.info("average rating given");
		return ratingService.findAvgRating();
	}
	
	/*@GetMapping("/rating/{meeting_id}")
	public List<String> findAvgRatingByEventId(@PathVariable("meeting_id") Long meeting_id) {
		logger.info("average rating given");
		return ratingService.findAvgRatingByEventId(meeting_id);
	}*/
}
