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

import com.gregory.AMSList.domain.Manga;
import com.gregory.AMSList.domain.dtos.MangaDTO;
import com.gregory.AMSList.services.MangaService;

@RestController
@RequestMapping(value = "/mangas")
public class MangaResource {
	
	@Autowired
	private MangaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MangaDTO> findById(@PathVariable Integer id){
		
		Manga manga = service.findById(id);
		
		return ResponseEntity.ok().body(new MangaDTO(manga));
	}
	
	@GetMapping
	public ResponseEntity<List<MangaDTO>> findAll(){
		
		List<Manga> list = service.findAll();
		List<MangaDTO> listDTO = list.stream().map(x -> new MangaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<MangaDTO> create(@Valid @RequestBody MangaDTO objDTO){
		
		Manga newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MangaDTO> update(@PathVariable Integer id, @Valid @RequestBody MangaDTO objDTO){
		
		Manga newObj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new MangaDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MangaDTO> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}







