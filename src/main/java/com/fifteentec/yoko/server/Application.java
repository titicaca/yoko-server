package com.fifteentec.yoko.server;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@ComponentScan
@RestController
@EnableAutoConfiguration
public class Application {
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }
	
	public static void main(String[] args) throws Exception {
	   	 SpringApplication.run(Application.class, args);
	}
}
