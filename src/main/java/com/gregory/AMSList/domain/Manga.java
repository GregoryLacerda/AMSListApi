/*package com.gregory.AMSList.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("Manga")
public class Manga extends Story{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "mangas")
	private Set<User> users = new HashSet<>();
	
	public Manga() {
		super();
	}

	public Manga(Integer id, String name, String coverImage, String siteLink, String description, Double season,
			Double episode) {
		super(id, name, coverImage, siteLink, description, season, episode);
	}

	public Set<User> getUsers() {
		return users;
	}

	public void addUsers(Set<User> users) {
		this.users.addAll(users);
	}
	
}
*/