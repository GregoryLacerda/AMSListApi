package com.gregory.AMSList.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.Storys;
import com.gregory.AMSList.domain.User;
import com.gregory.AMSList.domain.dtos.BookMarkDTO;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.repositories.StorysRepository;
import com.gregory.AMSList.services.exceptions.ObjectNotFoundException;

@Service
public class BookMarkService {
	
	@Autowired
	private BookMarkRepository repository;
	@Autowired
	StorysRepository storyService;
	@Autowired
	UserService userService;
	
	public BookMark findById(Integer id) {		
		Optional<BookMark> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, id: " + id));		
	}
	
	public List<BookMark> findAll() {
		return repository.findAll();
	}
	
	public BookMark create(BookMarkDTO objDTO) {
		
		objDTO.setId(null);
		BookMark newObj = newBoorkMark(objDTO);
		return repository.save(newObj);
	}
	

	public BookMark update(Integer id, @Valid BookMarkDTO objDTO ) {
		
		objDTO.setId(id);
		BookMark oldObj = findById(id);
		oldObj = newBoorkMark(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		BookMark obj = findById(id);
		repository.deleteById(obj.getId());
	}
	
	public BookMark newBoorkMark(BookMarkDTO obj) {
		
		User user = userService.findById(obj.getUser()); 
		
		Storys story = storyService.findById(obj.getStory()).orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
		
		BookMark bookMark = new BookMark();
		
		if (obj.getId() != null) {
			bookMark.setId(obj.getId());
		}
		
		bookMark.setStory(story);
		bookMark.setUser(user);
		bookMark.setStatus(obj.getStatus());
		bookMark.setSeason(obj.getSeason());
		bookMark.setEpisode(obj.getEpisode());
		bookMark.setStoryType(obj.getStoryType());
		
		return bookMark;
		
	}
	
}







