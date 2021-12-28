package com.moda.api_java_sts.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.moda.api_java_sts.entities.Category;
import com.moda.api_java_sts.entities.Order;
import com.moda.api_java_sts.entities.User;
import com.moda.api_java_sts.entities.enums.OrderStatus;
import com.moda.api_java_sts.repositories.CategoryRepository;
import com.moda.api_java_sts.repositories.OrderRepository;
import com.moda.api_java_sts.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		User maria = new User(null, "Maria", "maria@gmail.com", "1111-2222", "123456");
		User jurandir = new User(null, "Jurandir", "jurandir@gmail.com", "3333-4444", "789123");
		
		userRepository.saveAll(Arrays.asList(maria, jurandir));
		
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, maria); 
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, jurandir); 
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, maria);
		
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
		
		Category eletronics = new Category(null, "Eletronics");
		Category books = new Category(null, "Books");
		Category games = new Category(null, "Games");
		
		categoryRepository.saveAll(Arrays.asList(eletronics, books, games));
	}
}
