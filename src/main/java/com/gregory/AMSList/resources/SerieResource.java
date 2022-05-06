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

import com.gregory.AMSList.domain.Serie;
import com.gregory.AMSList.domain.dtos.SerieDTO;
import com.gregory.AMSList.services.SerieService;

@RestController
@RequestMapping(value = "/series")
public class SerieResource {
	
	@Autowired
	private SerieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SerieDTO> findById(@PathVariable Integer id){
		
		Serie serie = service.findById(id);
		
		return ResponseEntity.ok().body(new SerieDTO(serie));
	}
	
	@GetMapping
	public ResponseEntity<List<SerieDTO>> findAll(){
		
		List<Serie> list = service.findAll();
		List<SerieDTO> listDTO = list.stream().map(x -> new SerieDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<SerieDTO> create(@Valid @RequestBody SerieDTO objDTO){
		
		Serie newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SerieDTO> update(@PathVariable Integer id, @Valid @RequestBody SerieDTO objDTO){
		
		Serie newObj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new SerieDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<SerieDTO> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}







