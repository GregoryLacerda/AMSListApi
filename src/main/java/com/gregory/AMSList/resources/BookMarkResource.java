package com.gregory.AMSList.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.gregory.AMSList.domain.Manga;
import com.gregory.AMSList.domain.dtos.MangaDTO;
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
	
	@GetMapping
	public ResponseEntity<List<BookMark>> findAll(){
		
		List<BookMark> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BookMarkDTO> findById(@PathVariable Integer id){

		BookMark bookMark = service.findById(id);

		return ResponseEntity.ok().body(new BookMarkDTO(bookMark));
	}
	
	@PostMapping()
	public ResponseEntity<BookMarkDTO> create(@RequestBody BookMarkDTO bookMarkDTO){

		BookMark obj = service.create(bookMarkDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
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







