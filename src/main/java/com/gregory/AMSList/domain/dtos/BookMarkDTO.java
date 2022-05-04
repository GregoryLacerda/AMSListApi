package com.gregory.AMSList.domain.dtos;

import java.io.Serializable;

import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.Storys;
import com.gregory.AMSList.domain.User;
import com.gregory.AMSList.domain.enums.Status;

public class BookMarkDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Status status;
	private String storyType;
	private User user;
	private Storys storys;

	
	public BookMarkDTO() {
		super();
	}

	public BookMarkDTO(BookMark obj) {
		super();
		this.id = obj.getId();
		this.storys = obj.getInfos();
		this.user = obj.getUser();
		this.status = getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Storys getStory() {
		return storys;
	}

	public void setStory(Storys storys) {
		this.storys = storys;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
