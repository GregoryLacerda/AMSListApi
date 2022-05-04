package com.gregory.AMSList.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Storys implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	protected String name;
	protected String poster;
	protected String site;
	protected String description;
	protected Double season;
	protected Double episode;
	
	@OneToMany(mappedBy = "storys")
	protected List<BookMark> bookMark = new ArrayList<>(); 	

	public Storys() {
		super();
	}

	public Storys(Integer id, String name, String coverImage, String site, String description, Double season,
			Double episode) {
		super();
		this.id = id;
		this.name = name;
		this.poster = coverImage;
		this.site = site;
		this.description = description;
		this.season = season;
		this.episode = episode;
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

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	@JsonIgnore
	public List<BookMark> getBookMark() {
		return bookMark;
	}

	public void setBookMark(List<BookMark> bookMark) {
		this.bookMark = bookMark;
	}
	
	
	/**
	 * Get the value of Discriminator column normaly named by "DTYPE"
	 * 
	 * @return String DiscriminatorValue
	 */
	@JsonIgnore
	public String getDecriminationValue() {
		/*String[] className = this.getClass().getName().split(".");//split string by .
		
		return className[4];//return the 5th word of com.gregory.AMSList.domain.nameClass*/
		
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();

	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Storys other = (Storys) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}
