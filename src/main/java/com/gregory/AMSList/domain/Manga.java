package com.gregory.AMSList.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.gregory.AMSList.domain.dtos.MangaDTO;

@Entity
@DiscriminatorValue("Manga")
public class Manga extends Storys{
	private static final long serialVersionUID = 1L;
	
	public Manga() {
		super();
	}

	public Manga(Integer id, String name, String poster, String site, String description, Double totalSeason,
			Double totalEpisode) {
		super(id, name, poster, site, description, totalSeason, totalEpisode);
	}

	public Manga(MangaDTO obj) {
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