package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
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
	public Appointment getAppointment(@PathVariable("appointment_id") Long appointment_id,Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Appointment appointment = appointmentRepository.findById(appointment_id);
		if(appointment.getUser() == user)
			return appointment;
		else 
			return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addAppointment(Principal principal, @RequestBody Appointment postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Appointment appointment = new Appointment();
		appointment.setName(postclass.getName());
		appointment.setTimebegin(postclass.getTimebegin());
		appointment.setTimeend(postclass.getTimeend());
		appointment.setLocation(postclass.getLocation());
		appointment.setIntroduction(postclass.getIntroduction());
		appointment.setPeopleall(postclass.getPeopleall());
		appointment.setPicturelink(postclass.getPicturelink());
		appointment.setDetaillink(postclass.getDetaillink());
		appointment.setUser(user);
		try{
			appointmentRepository.save(appointment);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);	
	}
	

}
