package com.gregory.AMSList.domain.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.enums.Status;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookMarkDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Status status;
	private String storyType;
	private Double season;
	private Double episode;
	private Integer user;
	private Integer story;

	public BookMarkDTO() {
	}
	
	public BookMarkDTO(Integer id, Status status, String storyType, Integer user, Integer storys, Double season, Double episode) {
		super();
		this.id = id;
		this.status = status;
		this.storyType = storyType;
		this.user = user;
		this.story = storys;
		this.season = season;
		this.episode = episode;
	}

	public BookMarkDTO(BookMark obj) {
		super();
		this.id = obj.getId();
		this.story = obj.getStory().getId();
		this.user = obj.getUser().getId();
		this.status = obj.getStatus();
		this.season = obj.getSeason();
		this.episode = obj.getEpisode();
		this.storyType = obj.getStoryType();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStory() {
		return story;
	}

	public void setStory(Integer storys) {
		this.story = storys;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
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

	public Double getSeason() {
		return season;
	}

	public void setSeason(Double season) {
		this.season = season;
	}

	public Double getEpisode() {
		return episode;
	}

	public void setEpisode(Double episode) {
		this.episode = episode;
	}

}
