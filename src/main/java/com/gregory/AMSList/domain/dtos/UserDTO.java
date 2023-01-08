package com.gregory.AMSList.domain.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.User;
import com.gregory.AMSList.domain.enums.Profile;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	@NotNull(message = "NAME is required")
	private String name;
	@NotNull(message = "EMAIL is required")
	private String email;
	@NotNull(message = "PASSWORD is required")
	private String password;
	private Set<Integer> profiles = new HashSet<>();
	
	private Set<BookMark> storys = new HashSet<>(); 

	public UserDTO() {
		super();
		addProfile(Profile.USER);
	}

	public UserDTO(User obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.storys = obj.getStorys();
		addProfile(Profile.USER);
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@JsonIgnore
	public Set<Profile> getProfile() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCodigo());
	}

	@JsonIgnore
	public Set<BookMark> getStorys() {
		return storys;
	}

	public void setStory(Set<BookMark> story) {
		this.storys = story;
	}
	
}
