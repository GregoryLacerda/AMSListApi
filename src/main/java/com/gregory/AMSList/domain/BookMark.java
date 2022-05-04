package com.gregory.AMSList.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gregory.AMSList.domain.dtos.BookMarkDTO;
import com.gregory.AMSList.domain.enums.Status;

@Entity
@Table(name = "bookmarks")
public class BookMark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Status status;
	
	private String storyType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "story_id")
	private Storys storys;
	
	public BookMark() {
		super();
	}
	public BookMark(BookMarkDTO obj) {
		super();
		this.id = obj.getId();
		this.storys = obj.getStory();
		this.user = obj.getUser();
		this.status = getStatus();
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Storys getInfos() {
		return storys;
	}

	public void setStory(Storys storys) {
		this.storys = storys;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getStoryType() {
		return storyType;
	}
	public void setStoryType(String storyType) {
		this.storyType = storyType;
	}

}
