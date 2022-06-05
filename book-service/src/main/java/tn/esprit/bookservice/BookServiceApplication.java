package tn.esprit.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import tn.esprit.bookservice.repository.CategoryRepository;
import tn.esprit.bookservice.model.Category;

@SpringBootApplication
@EnableEurekaClient
public class BookServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(BookServiceApplication.class, args);

		CategoryRepository categoryRepository = configurableApplicationContext.getBean(CategoryRepository.class);
		Category c1 = new Category(1, "action");
		Category c2 = new Category(2, "drama");
		Category c3 = new Category(3, "science");
		Category c4 = new Category(4, "comedy");
		categoryRepository.save(c1);
		categoryRepository.save(c2);
		categoryRepository.save(c3);
		categoryRepository.save(c4);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
