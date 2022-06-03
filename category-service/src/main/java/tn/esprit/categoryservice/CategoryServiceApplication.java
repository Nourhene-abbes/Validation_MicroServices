package tn.esprit.categoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tn.esprit.categoryservice.model.Category;
import tn.esprit.categoryservice.repository.CategoryRepository;
import tn.esprit.categoryservice.service.CategoryService;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaClient
public class CategoryServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
		SpringApplication.run(CategoryServiceApplication.class, args);

		CategoryRepository categoryRepository = configurableApplicationContext.getBean(CategoryRepository.class);
		Category c1 = new Category(0, "Kteb 9ira2a");
		Category c2 = new Category(0, "Kteb a");
		Category c3 = new Category(0, "Kteb b");
		Category c4 = new Category(0, "Kteb c");
		categoryRepository.save(c1);
		categoryRepository.save(c2);
		categoryRepository.save(c3);
		categoryRepository.save(c4);


	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
