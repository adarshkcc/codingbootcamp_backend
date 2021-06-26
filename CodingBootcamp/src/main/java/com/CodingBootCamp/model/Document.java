package com.CodingBootCamp.model;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="documents")
public class Document {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="size")
	private long size;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name="content", length=100000)
	private byte[] content;
	
	private Long meeting_id;
	
	
	
	public Long getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(Long meeting_id) {
		this.meeting_id = meeting_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Document() {
		
		// TODO Auto-generated constructor stub
	}
	public Document(long id, String name, long size) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
	}
	
	

	
}

