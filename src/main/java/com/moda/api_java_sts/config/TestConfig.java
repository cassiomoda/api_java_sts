package com.moda.api_java_sts.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.moda.api_java_sts.entities.User;
import com.moda.api_java_sts.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User maria = new User(null, "Maria", "maria@gmail.com", "1111-2222", "123456");
		User jurandir = new User(null, "Jurandir", "jurandir@gmail.com", "3333-4444", "789123");
		
		userRepository.saveAll(Arrays.asList(maria, jurandir));
	}
}
