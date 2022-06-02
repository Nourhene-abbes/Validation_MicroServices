package tn.esprit.userservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.userservice.controller.UserController;
import tn.esprit.userservice.model.User;
import tn.esprit.userservice.repository.UserRepository;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}



	@Bean
	ApplicationRunner init(UserController Controller ) {
		User u = new User("nounou", "nourhene.abbes@esprit.tn", 54360346, "nounou", "1234", 0, "hhhhhhhhhh",
				1234);
		return (args) -> { Controller.Adduser(u);
// save
Controller.getAllUsers();

// fetch




		};

	}
}