package com.fifteentec.yoko.server;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.UserRepository;


@ComponentScan
@RestController
@EnableAutoConfiguration
public class Application {
	@RequestMapping("/home")
    String home() {
        return "Hello World!";
    }
	
	@Bean
	CommandLineRunner init(UserRepository accountRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(
						a -> {
							accountRepository.save(new User(a,
									"password"));
						});
	}
	
	public static void main(String[] args) throws Exception {
	   	 SpringApplication.run(Application.class, args);
	}
}
