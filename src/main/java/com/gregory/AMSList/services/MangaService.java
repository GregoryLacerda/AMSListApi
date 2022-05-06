package com.gregory.AMSList.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.Manga;
import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.Storys;
import com.gregory.AMSList.domain.dtos.MangaDTO;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.repositories.MangaRepository;
import com.gregory.AMSList.repositories.StorysRepository;
import com.gregory.AMSList.services.exceptions.DataIntegrityViolationException;
import com.gregory.AMSList.services.exceptions.ObjectNotFoundException;

@Service
public class MangaService {
	
	@Autowired
	private MangaRepository repository;
	
	@Autowired
	private StorysRepository storysRepository;
	@Autowired
	private BookMarkRepository bookMarkRepository;	
	
	public Manga findById(Integer id) {		
		Optional<Manga> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, id: " + id));		
	}
	
	public List<Manga> findAll() {
		return repository.findAll();
	}
	
	public Manga create(MangaDTO objDTO) {
		
		objDTO.setId(null);
		verifyDuplicateNameAndSite(objDTO);
		Manga newObj = new Manga(objDTO);
		return repository.save(newObj);
	}
	

	public Manga update(Integer id, @Valid MangaDTO objDTO ) {
		
		objDTO.setId(id);
		Manga oldObj = findById(id);
		oldObj = new Manga(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Manga obj = findById(id);
		List<BookMark> bookmarks = bookMarkRepository.findAllByStory(obj.getDecriminationValue());
		if (bookmarks.size() > 0) {
			throw new DataIntegrityViolationException("Can't delete, this manga was favorited by other users");
		}
		repository.deleteById(obj.getId());
	}
	
	/**
	 * Verify if the name or site link was already inserted by the same user
	 * 
	 * @param MangaDTO objDTO
	 */
	public void verifyDuplicateNameAndSite(MangaDTO objDTO) {
		
		Optional<Storys> obj = storysRepository.findByName(objDTO.getName());
		if (obj.isPresent() && obj.get().getId() == objDTO.getId()) {
			throw new DataIntegrityViolationException("You already save this Manga"); 
		}
		
		obj = storysRepository.findBySite(objDTO.getSite());
		if (obj.isPresent() && obj.get().getId() == objDTO.getId()) {
			throw new DataIntegrityViolationException("You already save a Manga with this site link");
		}
	}
	
}







