package com.gregory.AMSList.domain.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.enums.Status;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookMarkDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Status status;
	private String storyType;
	private Double currentSeason;
	private Double currentEpisode;
	private Integer user;
	private Integer story;

	public BookMarkDTO() {
	}
	
	public BookMarkDTO(Integer id, Status status, String storyType, Integer user, Integer storys, Double currentSeason, Double currentEpisode) {
		super();
		this.id = id;
		this.status = status;
		this.storyType = storyType;
		this.user = user;
		this.story = storys;
		this.currentSeason = currentSeason;
		this.currentEpisode = currentEpisode;
	}

	public BookMarkDTO(BookMark obj) {
		super();
		this.id = obj.getId();
		this.story = obj.getStory().getId();
		this.user = obj.getUser().getId();
		this.status = obj.getStatus();
		this.currentSeason = obj.getCurrentSeason();
		this.currentEpisode = obj.getCurrentEpisode();
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

	public Double getCurrentSeason() {
		return currentSeason;
	}

	public void setCurrentSeason(Double season) {
		this.currentSeason = season;
	}

	public Double getCurrentEpisode() {
		return currentEpisode;
	}

	public void setCurrentEpisode(Double episode) {
		this.currentEpisode = episode;
	}

}
