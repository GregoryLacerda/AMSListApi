package com.gregory.AMSList.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gregory.AMSList.domain.Storys;

public interface StoryRepository extends JpaRepository<Storys, Integer>{
	
	Optional<Storys> findByName(String name);
	Optional<Storys> findBySite(String site);
}
