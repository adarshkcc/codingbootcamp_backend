package com.CodingBootCamp.repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingBootCamp.dao.RatingDAO;
import com.CodingBootCamp.model.Rating;
import com.CodingBootCamp.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingDAO ratingDAO;
	private static final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);
	
	
	@Override
	public void getRating(Rating rating) {
		logger.info("get rating called");
	
			ratingDAO.save(rating);
		
	}                   
                     
	@Override
	public List<String> findAvgRating() {
		List<Rating>ratingList=new ArrayList<Rating>();
		ratingList=ratingDAO.findAll();
		double totalRatingOfMansi=0; 	double totalRatingOfAditya=0; 	double totalRatingOfNaveen=0; 	double totalRatingOfPrakhar=0; 
	int	countMansi=0; int	countAditya=0; int	countNaveen=0; int	countPrakhar=0;
	
		for(Rating rating:ratingList) {
			if("Mansi Tripathi".equals(rating.getName())) {
				logger.info(" Mansi's rating Added");
				System.out.println("initial rating "+rating.getRating());
				totalRatingOfMansi=totalRatingOfMansi+rating.getRating();
				countMansi++;
			}
			else if("Aditya Ranjan".equals(rating.getName())) {
				logger.info(" Aditya's rating Added");
				totalRatingOfAditya=totalRatingOfAditya+rating.getRating();
				countAditya++;
			}
			
			else if("Naveen Kumar".equals(rating.getName())) {
				logger.info(" Naveen's rating Added");
				totalRatingOfNaveen=totalRatingOfNaveen+rating.getRating();
				countNaveen++;
			}
			
			else if("Prakhar".equals(rating.getName())) {
				logger.info(" Prakhar's rating Added");
				totalRatingOfPrakhar=totalRatingOfPrakhar+rating.getRating();
				countPrakhar++;
			}
		}
		List<String> avglist=new ArrayList<String>();
		if(countMansi==0)
			avglist.add("");
		else {
		double Mansiavg=totalRatingOfMansi/countMansi;
		
		
		  Mansiavg =Double.parseDouble(new DecimalFormat("##.#").format(Mansiavg));
		  avglist.add(""+Mansiavg);
		}
		if(countAditya==0)
			avglist.add("");
		else {
		double Adityaavg=totalRatingOfAditya/countAditya;
		  Adityaavg =Double.parseDouble(new DecimalFormat("##.#").format(Adityaavg));
		  avglist.add(""+Adityaavg);
		}
		if(countNaveen==0)
			avglist.add("");
		else {
		double Naveenavg=totalRatingOfNaveen/countNaveen;
		  Naveenavg =Double.parseDouble(new DecimalFormat("##.#").format(Naveenavg));
		avglist.add(""+Naveenavg);
		}
		if(countPrakhar==0)
			avglist.add("");
		else {
		double Prakharavg=totalRatingOfPrakhar/countPrakhar;
		  Prakharavg =Double.parseDouble(new DecimalFormat("##.#").format(Prakharavg));
		avglist.add(""+Prakharavg);
		}
		
		
		
		return avglist;
	}

	

}