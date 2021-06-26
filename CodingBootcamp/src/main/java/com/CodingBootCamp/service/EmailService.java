package com.CodingBootCamp.service;

import com.CodingBootCamp.model.EmailTemplate;

public interface EmailService {

	
	public boolean sendEmail( EmailTemplate email,Long meeting_id);

}
