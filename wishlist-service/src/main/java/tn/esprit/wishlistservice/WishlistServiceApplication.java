package tn.esprit.wishlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import tn.esprit.wishlistservice.model.Book;
import tn.esprit.wishlistservice.model.Category;
import tn.esprit.wishlistservice.model.User;
import tn.esprit.wishlistservice.repository.BookRepository;
import tn.esprit.wishlistservice.repository.CategoryRepository;
import tn.esprit.wishlistservice.repository.UserRepository;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin("*")
public class WishlistServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(WishlistServiceApplication.class, args);

		UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
		User u = new User("nounou", "nourhene.abbes@esprit.tn", 54360346, "nounou", "1234", 0, "hhhhhhhhhh",
				1234);
		userRepository.save(u);

		CategoryRepository categoryRepository = configurableApplicationContext.getBean(CategoryRepository.class);
		Category c1 = new Category(1, "action");
		Category c2 = new Category(2, "drama");
		Category c3 = new Category(3, "science");
		Category c4 = new Category(4, "comedy");
		categoryRepository.save(c1);
		categoryRepository.save(c2);
		categoryRepository.save(c3);
		categoryRepository.save(c4);

		BookRepository bookRepository = configurableApplicationContext.getBean(BookRepository.class);

		Book book = new Book(1,"titre11", 200, "rades", "sun", new Date(), 6,
				"status", c1, "image", 48, "maha", false);
		Book book2 = new Book(2,"titre12", 400, "hlif", "moon", new Date(), 10,
				"status", c2, "image", 32, "maha", false);

		bookRepository.save(book);
		bookRepository.save(book2);

		System.out.println(bookRepository.findAll());
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}