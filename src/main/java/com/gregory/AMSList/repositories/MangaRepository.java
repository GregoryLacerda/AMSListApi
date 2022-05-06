package com.gregory.AMSList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gregory.AMSList.domain.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer>{
	
	
}
