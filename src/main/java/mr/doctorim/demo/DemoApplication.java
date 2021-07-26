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


	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
		     userService.saveRole(new Role(null, RolesEnum.DOCTOR));
		     userService.saveRole(new Role(null, RolesEnum.PATIENT));
		     userService.saveRole(new Role(null, RolesEnum.ADMIN));

		     userService.saveUser(new User(null,"ABBAD","37603030","1234",new ArrayList<>()));
		     userService.saveUser(new User(null,"ABBAD_TN","+21623201318","1234",new ArrayList<>()));
		     userService.saveUser(new User(null,"Bankily","*888#","1234",new ArrayList<>()));

		     userService.addRoleToUser("37603030","ADMIN");
		     userService.addRoleToUser("+21623201318","DOCTOR");
		     userService.addRoleToUser("*888#","PATIENT");
		};
	}
}
