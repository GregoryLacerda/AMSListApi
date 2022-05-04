package com.gregory.AMSList.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.dtos.BookMarkDTO;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.services.exceptions.ObjectNotFoundException;

@Service
public class BookMarkService {
	
	@Autowired
	private BookMarkRepository repository;
	
	public BookMark findById(Integer id) {		
		Optional<BookMark> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, id: " + id));		
	}
	
	public List<BookMark> findAll() {
		return repository.findAll();
	}
	
	public BookMark create(BookMarkDTO objDTO) {
		
		objDTO.setId(null);
		BookMark newObj = new BookMark(objDTO);
		return repository.save(newObj);
	}
	

	public BookMark update(Integer id, @Valid BookMarkDTO objDTO ) {
		
		objDTO.setId(id);
		BookMark oldObj = findById(id);
		oldObj = new BookMark(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		BookMark obj = findById(id);
		repository.deleteById(obj.getId());
	}
	

	
}







