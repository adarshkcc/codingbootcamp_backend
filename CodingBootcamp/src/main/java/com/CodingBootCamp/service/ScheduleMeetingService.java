package com.CodingBootCamp.service;

import java.util.List;


import com.CodingBootCamp.model.ScheduleMeeting;

public interface ScheduleMeetingService {

	 public  ScheduleMeeting  getMeetingById(Long meeting_id);
	 public String scheduleMeeting(ScheduleMeeting m);
	 public  List<ScheduleMeeting>  getMeetingDetails();

}
