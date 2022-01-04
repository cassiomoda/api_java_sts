package com.moda.api_java_sts.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.moda.api_java_sts.entities.Category;
import com.moda.api_java_sts.entities.Order;
import com.moda.api_java_sts.entities.Product;
import com.moda.api_java_sts.entities.User;
import com.moda.api_java_sts.entities.enums.OrderStatus;
import com.moda.api_java_sts.repositories.CategoryRepository;
import com.moda.api_java_sts.repositories.OrderRepository;
import com.moda.api_java_sts.repositories.ProductRepository;
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
	@Autowired
	private ProductRepository productRepository;

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
		
		Product lotr = new Product(null, "The Lord of the Rings", "book The Lord of the Rings", 90.5, ""); 
		Product smartTv = new Product(null, "Smart TV", "Smart TV top", 2190.0, ""); 
		Product macBkPro = new Product(null, "Macbook Pro", "Macbook Pro, you pay a lot for someting you could buy for a very low price", 1250.0, ""); 
		Product pcGamer = new Product(null, "PC Gamer", "Yes, it has lots of RGB!", 1200.0, ""); 
		Product railsOfDummies = new Product(null, "Rails for Dummies", "I think you need it.", 100.99, ""); 
		
		lotr.getCategories().add(books);
		smartTv.getCategories().add(eletronics);
		smartTv.getCategories().add(games);
		macBkPro.getCategories().add(eletronics);
		pcGamer.getCategories().add(games);
		railsOfDummies.getCategories().add(books);
		
		productRepository.saveAll(Arrays.asList(lotr, smartTv, macBkPro, pcGamer, railsOfDummies));
	}
}
