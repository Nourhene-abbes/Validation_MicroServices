package tn.esprit.onlinebookservice;

import java.util.Date;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import tn.esprit.onlinebookservice.controller.OnlineBookController;
import tn.esprit.onlinebookservice.model.Book;
import tn.esprit.onlinebookservice.model.OnlineBook;

@SpringBootApplication
@EnableEurekaClient
public class OnlineBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(OnlineBookController Controller ) {
		OnlineBook u = new OnlineBook();
		u.setUrl("url kouissi");
		Book b = new Book ( "titre",  123, "daranchr", "summary", new Date(), 2,
				"status", "rafaa");
		return (args) -> { Controller.addOnlineBook(u);;
// save
Controller.getBookList();

// fetch




		};

	}
}
