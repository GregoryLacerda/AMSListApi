package com.gregory.AMSList.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.AMSList.domain.User;
import com.gregory.AMSList.domain.dtos.UserDTO;
import com.gregory.AMSList.repositories.UserRepository;
import com.gregory.AMSList.services.exceptions.DataIntegrityViolationException;
import com.gregory.AMSList.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User findById(Integer id) {		
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, id: " + id));		
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User create(UserDTO objDTO) {
		
		objDTO.setId(null);
		validRegisteredEmail(objDTO);
		User newObj = new User(objDTO);
		return repository.save(newObj);
	}
	

	public User update(Integer id, UserDTO objDTO ) {
		
		objDTO.setId(id);
		User oldObj = findById(id);
		oldObj = new User(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		User obj = findById(id);
		
		repository.deleteById(obj.getId());
	}
	
	/**
	 * Verify if have the same e-mail registered
	 * 
	 * @param UserDTO objDTO
	 */
	public void validRegisteredEmail(UserDTO objDTO) {
		
		Optional<User> obj = repository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail already registered");
		}
	}
	
}







