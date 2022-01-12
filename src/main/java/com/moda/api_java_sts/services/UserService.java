package com.moda.api_java_sts.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.moda.api_java_sts.entities.User;
import com.moda.api_java_sts.repositories.UserRepository;
import com.moda.api_java_sts.services.exceptions.DatabaseException;
import com.moda.api_java_sts.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void deleteById(Long id) {
		try {
			userRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	private void updateData(User userDb, User user) {
		userDb.setEmail(user.getEmail());
		userDb.setName(user.getName());
		userDb.setPhone(user.getPhone());
		userDb.setPwd(user.getPwd());
	}
	
	public User update(Long id, User user) {
		try {
			User userDb = userRepository.getById(id);
			updateData(userDb, user);
			return userRepository.save(userDb);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
