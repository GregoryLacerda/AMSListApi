package com.gregory.AMSList.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.Serie;
import com.gregory.AMSList.domain.Storys;
import com.gregory.AMSList.domain.dtos.SerieDTO;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.repositories.SerieRepository;
import com.gregory.AMSList.repositories.StorysRepository;
import com.gregory.AMSList.services.exceptions.DataIntegrityViolationException;
import com.gregory.AMSList.services.exceptions.ObjectNotFoundException;

@Service
public class SerieService {
	
	@Autowired
	private SerieRepository repository;
	
	@Autowired
	private StorysRepository storysRepository;
	@Autowired
	private BookMarkRepository bookMarkRepository;	
	
	public Serie findById(Integer id) {		
		Optional<Serie> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, id: " + id));		
	}
	
	public List<Serie> findAll() {
		return repository.findAll();
	}
	
	public Serie create(SerieDTO objDTO) {
		
		objDTO.setId(null);
		verifyDuplicateNameAndSite(objDTO);
		Serie newObj = new Serie(objDTO);
		return repository.save(newObj);
	}
	

	public Serie update(Integer id, @Valid SerieDTO objDTO ) {
		
		objDTO.setId(id);
		Serie oldObj = findById(id);
		oldObj = new Serie(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Serie obj = findById(id);
		List<BookMark> serieRegistration = bookMarkRepository.findAllByStory(obj.getDecriminationValue());
		if (serieRegistration.size() > 0) {
			throw new DataIntegrityViolationException("Can't delete, this serie was favorited by other users");
		}
		repository.deleteById(obj.getId());
	}
	
	/**
	 * Verify if the name or site link was already inserted by the same user
	 * 
	 * @param SerieDTO objDTO
	 */
	public void verifyDuplicateNameAndSite(SerieDTO objDTO) {
		
		Optional<Storys> obj = storysRepository.findByName(objDTO.getName());
		if (obj.isPresent() && obj.get().getId() == objDTO.getId()) {
			throw new DataIntegrityViolationException("You already save this Serie"); 
		}
		
		obj = storysRepository.findBySite(objDTO.getSite());
		if (obj.isPresent() && obj.get().getId() == objDTO.getId()) {
			throw new DataIntegrityViolationException("You already save a Serie with this site link");
		}
	}
	
}







