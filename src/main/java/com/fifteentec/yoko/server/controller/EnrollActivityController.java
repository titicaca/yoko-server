package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/enrollactivity")  
@EnableAutoConfiguration

public class EnrollActivityController {
	
	@RequestMapping(method=RequestMethod.POST)
	public EnrollActivity addEnrollActivity(@RequestBody EnrollActivity postclass){
		EnrollActivity enrollactivity = new EnrollActivity();
		enrollactivity.setUser_id(postclass.getUser_id());
		enrollactivity.setActivity_id(postclass.getActivity_id());
		return enrollactivity;
	}

}
