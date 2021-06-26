package com.CodingBootCamp.service;

import java.util.List;

import com.CodingBootCamp.model.Rating;

public interface RatingService {

	void getRating(Rating rating);
	
	List<String> findAvgRating();

}
