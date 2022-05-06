package com.gregory.AMSList.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gregory.AMSList.domain.enums.Status;

@Entity
@Table(name = "bookmarks")
public class BookMark implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Status status;
	private String storyType;
	private Double season;
	private Double episode;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "story_id")
	private Storys storys;
	
	public BookMark() {
	}

	public BookMark(Integer id, Status status, String storyType, User user, Storys storys, Double season, Double episode) {
		super();
		this.id = id;
		this.status = status;
		this.storyType = storyType;
		this.user = user;
		this.storys = storys;
		this.season = season;
		this.episode = episode;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Storys getStory() {
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

	@Override
	public int hashCode() {
		return Objects.hash(storys, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookMark other = (BookMark) obj;
		return Objects.equals(storys, other.storys) && Objects.equals(user, other.user);
	}

	
}
