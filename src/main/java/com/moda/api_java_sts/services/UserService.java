package com.moda.api_java_sts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moda.api_java_sts.entities.User;
import com.moda.api_java_sts.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		
		return obj.get();
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	private void updateData(User userDb, User user) {
		userDb.setEmail(user.getEmail());
		userDb.setName(user.getName());
		userDb.setPhone(user.getPhone());
		userDb.setPwd(user.getPwd());
	}
	
	public User update(Long id, User user) {
		User userDb = userRepository.getById(id);
		updateData(userDb, user);
		return userRepository.save(userDb);
	}
}
