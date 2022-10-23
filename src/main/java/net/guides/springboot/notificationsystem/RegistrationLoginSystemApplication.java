package net.guides.springboot.notificationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("net")
@SpringBootApplication()
public class RegistrationLoginSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginSystemApplication.class, args);
	}
}

