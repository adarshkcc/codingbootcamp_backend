package com.CodingBootCamp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String contact;
	
	private String userName;
	private String verificationCode;
	private boolean enabled;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private ScheduleMeeting event;
	
	
	
	
	public User(Long id, String email, String contact, String userName, String verificationCode,
			boolean enabled) {
		super();
		this.id = id;
		this.email = email;
		this.contact = contact;
		
		this.userName = userName;
		this.verificationCode = verificationCode;
		this.enabled = enabled;
	}

	

	public Long getId() {
		return id;
	}

	public ScheduleMeeting getEvent() {
		return event;
	}



	public void setEvent(ScheduleMeeting event) {
		this.event = event;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public User() {
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
