package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/enrollappointment")  
@EnableAutoConfiguration

public class EnrollAppointmentController {
	
	@RequestMapping(method=RequestMethod.POST)
	public EnrollAppointment addEnrollAppointment(@RequestBody EnrollAppointment postclass){
		EnrollAppointment enrollappointment = new EnrollAppointment();
		enrollappointment.setUser_id(postclass.getUser_id());
		enrollappointment.setAppointment_id(postclass.getAppointment_id());
		return enrollappointment;
	}

}
