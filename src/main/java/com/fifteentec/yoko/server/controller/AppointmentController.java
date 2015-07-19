package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/appointment")  
@EnableAutoConfiguration
public class AppointmentController {
	@RequestMapping(method=RequestMethod.POST)
	public Appointment addAppointment(@RequestBody Appointment postclass){
		Appointment appointment = new Appointment();
		appointment.setName(postclass.getName());
		appointment.setAppointment_id(postclass.getAppointment_id());
		appointment.setTimebegin(postclass.getTimebegin());
		appointment.setTimeend(postclass.getTimeend());
		appointment.setLocation(postclass.getLocation());
		appointment.setIntroduction(postclass.getIntroduction());
		appointment.setPeopleall(postclass.getPeopleall());
		appointment.setPicturelink(postclass.getPicturelink());
		appointment.setDetaillink(postclass.getDetaillink());
		return appointment;
	}
	

}
