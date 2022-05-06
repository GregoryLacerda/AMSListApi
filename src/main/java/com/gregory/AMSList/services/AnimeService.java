package com.gregory.AMSList.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.Anime;
import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.Storys;
import com.gregory.AMSList.domain.dtos.AnimeDTO;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.repositories.AnimeRepository;
import com.gregory.AMSList.repositories.StorysRepository;
import com.gregory.AMSList.services.exceptions.DataIntegrityViolationException;
import com.gregory.AMSList.services.exceptions.ObjectNotFoundException;

@Service
public class AnimeService {
	
	@Autowired
	private AnimeRepository repository;
	
	@Autowired
	private StorysRepository storysRepository;
	@Autowired
	private BookMarkRepository bookMarkRepository;	
	
	public Anime findById(Integer id) {		
		Optional<Anime> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, id: " + id));		
	}
	
	public List<Anime> findAll() {
		return repository.findAll();
	}
	
	public Anime create(AnimeDTO objDTO) {
		
		objDTO.setId(null);
		verifyDuplicateNameAndSite(objDTO);
		Anime newObj = new Anime(objDTO);
		return repository.save(newObj);
	}
	

	public Anime update(Integer id, @Valid AnimeDTO objDTO ) {
		
		objDTO.setId(id);
		Anime oldObj = findById(id);
		oldObj = new Anime(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Anime obj = findById(id);
		List<BookMark> animeRegistration = bookMarkRepository.findAllByAnime(id);
		if (animeRegistration.size() > 0) {
			throw new DataIntegrityViolationException("Can't delete, this anime was favorited by other users");
		}
		repository.deleteById(obj.getId());
	}
	
	/**
	 * Verify if the name or site link was already inserted by the same user
	 * 
	 * @param AnimeDTO objDTO
	 */
	public void verifyDuplicateNameAndSite(AnimeDTO objDTO) {
		
		Optional<Storys> obj = storysRepository.findByName(objDTO.getName());
		if (obj.isPresent() && obj.get().getId() == objDTO.getId()) {
			throw new DataIntegrityViolationException("You already save this Anime"); 
		}
		
		obj = storysRepository.findBySite(objDTO.getSite());
		if (obj.isPresent() && obj.get().getId() == objDTO.getId()) {
			throw new DataIntegrityViolationException("You already save a Anime with this site link");
		}
	}
	
}







