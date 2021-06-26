package com.CodingBootCamp;



import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootCamp.controller.RatingController;
import com.CodingBootCamp.model.Rating;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class RatingControllerTest {

	@Autowired
	RatingController racon;
	@Test
	void getRatingTest() {
		
		Rating r=new Rating();
		r.setId(22);
		r.setName("Aditya");
		r.setRating(3);
		
		racon.getRating(r);
	}
	
	@Test
	void findAvgRatingTest() {
		
		racon.findAvgRating();
	}

}
