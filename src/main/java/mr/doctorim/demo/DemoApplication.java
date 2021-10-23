package mr.doctorim.demo;

import mr.doctorim.demo.model.Role;
import mr.doctorim.demo.model.User;
import mr.doctorim.demo.model.enumeration.RolesEnum;
import mr.doctorim.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passWordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
