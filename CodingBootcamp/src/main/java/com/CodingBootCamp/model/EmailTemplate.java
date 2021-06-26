package com.CodingBootCamp.model;

public class EmailTemplate {
	
	private String subject;
		private String msgBody;
	
	public EmailTemplate(String subject,  String msgBody) {
		super();
		this.subject = subject;
		
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public EmailTemplate() {
		
	}


}
