package com.gregory.AMSList.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gregory.AMSList.domain.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer>{
	
	@Query(value = "SELECT * FROM ANIME_REGISTRATION WHERE USER_ID = ?1",
			nativeQuery = true)
	List<BookMark> findAllByUser(Integer userId);

	@Query(value = "DELETE * FROM ANIME_REGISTRATION WHERE user_id = ?1",
			nativeQuery = true)
	void deleteByUser(Integer id);

	@Query(value = "SELECT * FROM ANIME_REGISTRATION WHERE ANIME_ID = ?1",
			nativeQuery = true)
	List<BookMark> findAllByAnime(Integer id);
	
}
