package com.gregory.AMSList.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.gregory.AMSList.domain.Serie;

public class SerieDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull(message = "NAME is required")
	private String name;
	@NotNull(message = "POSTER is required")
	private String poster;
	@NotNull(message = "SITE is required")
	private String site;
	@NotNull(message = "DESCRIPTION is required")
	private String description;
	@NotNull(message = "SEASON is required")
	private Double totalSeason;
	@NotNull(message = "EPISODE is required")
	private Double totalEpisode;	
	
	public SerieDTO() {
		super();
	}

	public SerieDTO(Serie obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.poster = obj.getPoster();
		this.site = obj.getSite();
		this.description = obj.getDescription();
		this.totalSeason = obj.getTotalSeason();
		this.totalEpisode = obj.getTotalEpisode();
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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String coverImage) {
		this.poster = coverImage;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String siteLink) {
		this.site = siteLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalSeason() {
		return totalSeason;
	}

	public void setTotalSeason(Double totalSeason) {
		this.totalSeason = totalSeason;
	}

	public Double getTotalEpisode() {
		return totalEpisode;
	}

	public void setTotalEpisode(Double totalEpisode) {
		this.totalEpisode = totalEpisode;
	}

}
