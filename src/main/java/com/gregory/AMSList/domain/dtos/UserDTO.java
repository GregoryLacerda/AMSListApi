package com.gregory.AMSList.domain.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	@NotNull(message = "NAME is required")
	private String name;
	@NotNull(message = "EMAIL is required")
	private String email;
	@NotNull(message = "PASSWORD is required")
	private String password;
	
	private Set<BookMark> storys = new HashSet<>(); 

	public UserDTO() {
		super();
	}

	public UserDTO(User obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.storys = obj.getStorys();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<BookMark> getStorys() {
		return storys;
	}

	public void setStory(Set<BookMark> story) {
		this.storys = story;
	}
	
}
