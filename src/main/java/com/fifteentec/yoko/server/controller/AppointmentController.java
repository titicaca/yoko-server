package com.fifteentec.yoko.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.AppointmentRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/appointment")  
@EnableAutoConfiguration
public class AppointmentController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@RequestMapping(value="/{appointment_id}",method=RequestMethod.GET)
	public Appointment getAppointment(@PathVariable("appointment_id") Long appointment_id){
		return appointmentRepository.findById(appointment_id);
	}
	
	@RequestMapping(value="/{user_id}",method=RequestMethod.POST)
	public Result addAppointment(@PathVariable("user_id") Long user_id, @RequestBody Appointment postclass){
		Appointment appointment = new Appointment();
		appointment.setName(postclass.getName());
		appointment.setTimebegin(postclass.getTimebegin());
		appointment.setTimeend(postclass.getTimeend());
		appointment.setLocation(postclass.getLocation());
		appointment.setIntroduction(postclass.getIntroduction());
		appointment.setPeopleall(postclass.getPeopleall());
		appointment.setPicturelink(postclass.getPicturelink());
		appointment.setDetaillink(postclass.getDetaillink());
		appointment.setUser(userRepository.findById(user_id));
		try{
			appointmentRepository.save(appointment);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);	
	}
	

}
