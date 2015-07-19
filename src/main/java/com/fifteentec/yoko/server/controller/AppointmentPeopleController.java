package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/appointmentpeople")  
@EnableAutoConfiguration

public class AppointmentPeopleController {
	@RequestMapping(method=RequestMethod.POST)
	public AppointmentPeople addAppointmentPeople(@RequestBody AppointmentPeople postclass){
		AppointmentPeople appointmentpeople = new AppointmentPeople();
		appointmentpeople.setAppointment_id(postclass.getAppointment_id());
		appointmentpeople.setPeopleenroll(0);
		return appointmentpeople;
	}

}
