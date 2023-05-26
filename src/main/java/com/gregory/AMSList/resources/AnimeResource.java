package com.gregory.AMSList.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gregory.AMSList.domain.Anime;
import com.gregory.AMSList.domain.dtos.AnimeDTO;
import com.gregory.AMSList.services.AnimeService;

@RestController
@RequestMapping(value = "/animes")
public class AnimeResource {
	
	@Autowired
	private AnimeService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnimeDTO> findById(@PathVariable Integer id){
		
		Anime anime = service.findById(id);
		return ResponseEntity.ok().body(new AnimeDTO(anime));
	}

	@GetMapping
	public ResponseEntity<List<AnimeDTO>> findAll(){
		
		List<Anime> list = service.findAll();
		List<AnimeDTO> listDTO = list.stream().map(x -> new AnimeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<AnimeDTO> create(@Valid @RequestBody AnimeDTO objDTO){
		
		Anime newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		AnimeDTO animeDTO = new AnimeDTO(newObj);

		return ResponseEntity.ok().body(animeDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AnimeDTO> update(@PathVariable Integer id, @Valid @RequestBody AnimeDTO objDTO){
		
		Anime newObj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new AnimeDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AnimeDTO> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}







