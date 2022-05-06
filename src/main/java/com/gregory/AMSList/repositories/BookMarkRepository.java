package com.gregory.AMSList.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gregory.AMSList.domain.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer>{
	
	@Query(value = "SELECT * FROM BOOKMARKS WHERE USER_ID = ?1 AND STORY_TYPE = ?2",
			nativeQuery = true)
	List<BookMark> findAllStoryByUser(Integer userId, String story);


	@Query(value = "SELECT * FROM BOOKMARKS WHERE STORY_TYPE = ?1",
			nativeQuery = true)
	List<BookMark> findAllByStory(String story);
	
}
