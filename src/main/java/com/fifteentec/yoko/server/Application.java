package com.fifteentec.yoko.server;

//import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

//@ComponentScan
@RestController
@EnableAutoConfiguration
public class Application {
	@RequestMapping("/home")
    String home() {
        return "Hello World!";
    }
}
