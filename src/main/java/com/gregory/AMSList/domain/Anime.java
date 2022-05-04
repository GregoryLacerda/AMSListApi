package com.gregory.AMSList.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.gregory.AMSList.domain.dtos.AnimeDTO;

@Entity
@DiscriminatorValue("Anime")
public class Anime extends Storys{
	private static final long serialVersionUID = 1L;
	
	public Anime() {
		super();
	}

	public Anime(Integer id, String name, String poster, String site, String description, Double season,
			Double episode) {
		super(id, name, poster, site, description, season, episode);
	}
	
	public Anime(AnimeDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.poster = obj.getPoster();
		this.site = obj.getSite();
		this.description = obj.getDescription();
		this.season = obj.getSeason();
		this.episode = obj.getEpisode();
	}


}
