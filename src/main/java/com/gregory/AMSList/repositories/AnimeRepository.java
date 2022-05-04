package com.gregory.AMSList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gregory.AMSList.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Integer>{
	
	
}
