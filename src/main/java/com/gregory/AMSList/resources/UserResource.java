package com.gregory.AMSList.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.gregory.AMSList.domain.User;
import com.gregory.AMSList.domain.dtos.UserDTO;
import com.gregory.AMSList.repositories.BookMarkRepository;
import com.gregory.AMSList.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	@Autowired
	private BookMarkRepository bookMarkRepository;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
		
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	
	@GetMapping(value = "/{id}/animes")
	public ResponseEntity<List<BookMark>> findAllAnimes(@PathVariable Integer id){
		
		List<BookMark> animes = bookMarkRepository.findAllStoryByUser(id, "Anime");
		
		return ResponseEntity.ok().body(animes);
	}


	@GetMapping(value = "/{id}/mangas")
	public ResponseEntity<List<BookMark>> findAllMangas(@PathVariable Integer id){
		
		List<BookMark> animes = bookMarkRepository.findAllStoryByUser(id, "Manga");
		
		return ResponseEntity.ok().body(animes);
	}


	@GetMapping(value = "/{id}/series")
	public ResponseEntity<List<BookMark>> findAllSeries(@PathVariable Integer id){
		
		List<BookMark> animes = bookMarkRepository.findAllStoryByUser(id, "Serie");
		
		return ResponseEntity.ok().body(animes);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDTO){
		
		User newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @Valid @RequestBody UserDTO objDTO){
		
		User obj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}





