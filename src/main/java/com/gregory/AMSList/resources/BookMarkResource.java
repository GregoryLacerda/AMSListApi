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

import com.gregory.AMSList.domain.BookMark;
import com.gregory.AMSList.domain.dtos.BookMarkDTO;
import com.gregory.AMSList.services.BookMarkService;

@RestController
@RequestMapping(value = "/bookmark")
public class BookMarkResource {
	
	@Autowired
	private BookMarkService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BookMarkDTO> findById(@PathVariable Integer id){
		
		BookMark anime = service.findById(id);
		
		return ResponseEntity.ok().body(new BookMarkDTO(anime));
	}
	
	@GetMapping
	public ResponseEntity<List<BookMarkDTO>> findAll(){
		
		List<BookMark> list = service.findAll();
		List<BookMarkDTO> listDTO = list.stream().map(x -> new BookMarkDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<BookMarkDTO> create(@Valid @RequestBody BookMarkDTO objDTO){
		
		BookMark newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BookMarkDTO> update(@PathVariable Integer id, @Valid @RequestBody BookMarkDTO objDTO){
		
		BookMark newObj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new BookMarkDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BookMarkDTO> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}







