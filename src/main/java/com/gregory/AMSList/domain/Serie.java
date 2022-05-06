package com.gregory.AMSList.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.gregory.AMSList.domain.dtos.SerieDTO;

@Entity
@DiscriminatorValue("Serie")
public class Serie extends Storys {
	private static final long serialVersionUID = 1L;
	
	public Serie() {
		super();
	}

	public Serie(Integer id, String name, String poster, String site, String description, Double totalSeason,
			Double totalEpisode) {
		super(id, name, poster, site, description, totalSeason, totalEpisode);
	}
	
	public Serie(SerieDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.poster = obj.getPoster();
		this.site = obj.getSite();
		this.description = obj.getDescription();
		this.totalSeason = obj.getTotalSeason();
		this.totalEpisode = obj.getTotalEpisode();
	}
}
