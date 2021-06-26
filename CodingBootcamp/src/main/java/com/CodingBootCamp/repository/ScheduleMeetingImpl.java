package com.CodingBootCamp.repository;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingBootCamp.customException.InvalidMeetingDateException;

import com.CodingBootCamp.dao.MeetingDAO;
import com.CodingBootCamp.model.ScheduleMeeting;
import com.CodingBootCamp.payload.CreateMeetingPayload;
import com.CodingBootCamp.service.ScheduleMeetingService;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class ScheduleMeetingImpl implements ScheduleMeetingService {

	@Value("${secret}")
	private String secret;

	@Value("${bigBlueButtonURL}")
	private String bigBlueButtonURL;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private MeetingDAO meetingDAO;
	private static final Logger logger = LoggerFactory.getLogger(ScheduleMeetingImpl.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyddMM");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

	@Override
	public String scheduleMeeting(ScheduleMeeting m) {
		try {

			if (m.getDate().isBefore(LocalDate.now())) {
				logger.error("Event Scheduled date is expired");
				throw new InvalidMeetingDateException("Event schedule date is expired!!!");

			} else if (m.getStart_time().compareTo(m.getEnd_time()) == 0) {
				return "Start time and End time cannot be same!!";
			} else if (m.getEnd_time().compareTo(m.getStart_time()) < 0) {
				return "Invalid Event End Time!!!";
			}
			List<ScheduleMeeting> meetings = meetingDAO.findAll();

			// first check whether any event on same date
			int datecheck = 0, timecheck = 0;

			for (ScheduleMeeting ms : meetings) {
				if (m.getDate().compareTo(ms.getDate()) == 0) {
					datecheck++;
				}
			}
			// datecheck!=0 defines that there are some meetings on same date
			// so now check whether time slot available or not
			if (datecheck != 0) {
				for (ScheduleMeeting ms : meetings) {
					if (m.getDate().compareTo(ms.getDate()) == 0) {

						boolean beforeSlot = (m.getStart_time().compareTo(ms.getStart_time()) < 0)
								&& (m.getEnd_time().compareTo(ms.getStart_time()) < 0);
						boolean afterSlot = (m.getStart_time().compareTo(ms.getEnd_time()) > 0)
								&& (m.getEnd_time().compareTo(ms.getEnd_time()) > 0);
						System.out.println("coming after slot- " + afterSlot + "coimg before slot " + beforeSlot);
						if (!beforeSlot && !afterSlot) {
							timecheck++;
						}
					}
				}

				if (timecheck == 0) {
					CreateMeetingPayload response = createMeeting(m);
					if ("SUCCESS".equalsIgnoreCase(response.getReturncode())) {
						meetingDAO.save(m);
						logger.info("Event Scheduled");
						return "Event Scheduled Successflly!!!";
					} else {
						return "Event Scheduled Successflly!!!";
					}
					//
				} else {
					return "Event slot not available!!!";
				}
			} else {
				System.out.println("create Meeting Called from here");

				CreateMeetingPayload response = createMeeting(m);
				if ("SUCCESS".equalsIgnoreCase(response.getReturncode())) {

					joinMeetingAsModerator(m);

					meetingDAO.save(m);

					return "Event Scheduled Successflly!!!";
				} else {
					return "error occured in creating a meeting";
				}
			}

		} catch (InvalidMeetingDateException e) {
			return e.getMessage();
		}

	}

	@Override
	public List<ScheduleMeeting> getMeetingDetails() {
		return meetingDAO.findAll();
	}

	@Override
	public ScheduleMeeting getMeetingById(Long meeting_id) {

		return meetingDAO.findById(meeting_id).get();
	}

	private CreateMeetingPayload createMeeting(ScheduleMeeting m) {

		String createMeeting = "name=" + m.getMeeting_name() + m.getId() + "&meetingID=bootcamp" + m.getId()
				+ "&moderatorPW=moderator" + "&attendeePW=attendee";
		CreateMeetingPayload response = restTemplate.getForObject(bigBlueButtonURL + "create?" + createMeeting
				+ "&checksum=" + DigestUtils.shaHex("create" + createMeeting + secret), CreateMeetingPayload.class);

		// printing the created url
		System.out.println(bigBlueButtonURL + "create?" + createMeeting + "&checksum="
				+ DigestUtils.shaHex("create" + createMeeting + secret));

		m.setMeeting_id(response.getMeetingID());
		m.setAttendeePswd(response.getAttendeePW());
		m.setModeratorPswd(response.getModeratorPW());

		System.out.println(response.getReturncode());

		return response;
	}

	private CreateMeetingPayload joinMeetingAsModerator(ScheduleMeeting m) {
		String joinMeeting = "fullName=Moderator" + m.getId() + "&meetingID=" + m.getMeeting_id() + "&password="
				+ m.getModeratorPswd() + "&redirect=true";
		// printing join as moderator url
		String moderatorUrl = bigBlueButtonURL + "join?" + joinMeeting + "&checksum="
				+ DigestUtils.shaHex("join" + joinMeeting + secret);
		m.setModerator_link(moderatorUrl);
		// calling joinMeetingAsAttendee()
		joinMeetingAsAttendee(m);

		CreateMeetingPayload response = restTemplate.getForObject(
				bigBlueButtonURL + "join?" + joinMeeting + "&checksum=" + DigestUtils.shaHex(joinMeeting + secret),
				CreateMeetingPayload.class);
		return response;
	}

	private CreateMeetingPayload joinMeetingAsAttendee(ScheduleMeeting m) {

		String joinMeeting = "fullName=Attendee" + m.getId() + "&meetingID=" + m.getMeeting_id() + "&password="
				+ m.getAttendeePswd() + "&redirect=true";
		// printing join as attendee url
		String attendeeUrl = bigBlueButtonURL + "join?" + joinMeeting + "&checksum="
				+ DigestUtils.shaHex("join" + joinMeeting + secret);
		m.setAttendee_link(attendeeUrl);
		CreateMeetingPayload response = restTemplate.getForObject(
				bigBlueButtonURL + "join?" + joinMeeting + "&checksum=" + DigestUtils.shaHex(joinMeeting + secret),
				CreateMeetingPayload.class);
		return response;
	}

}
