package com.fifteentec.yoko.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.AppointmentRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/enrollappointment")  
@EnableAutoConfiguration

public class EnrollAppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(value="/invitation",method=RequestMethod.POST)
	public Result addAppointmentInviteUser(@RequestBody UserAndAppointment postclass){
		Appointment appointment = appointmentRepository.findById(postclass.getAppointment_id());
		User user = userRepository.findById(postclass.getUser_id());	
		
		try{
			appointment.getInviteusers().add(user);
			appointmentRepository.save(appointment);
		}
		catch(Exception e){
			return new Result(false);
		}
			
		return new Result(true);	
	}
	
	@RequestMapping(value="/response",method=RequestMethod.POST)
	public Result addUserEnrollAppointment(@RequestBody UserAndAppointment postclass){
		Appointment appointment = appointmentRepository.findById(postclass.getAppointment_id());
		User user = userRepository.findById(postclass.getUser_id());	
		
		try{
			appointment.getEnrollusers().add(user);
			appointmentRepository.save(appointment);
		}
		catch(Exception e){
			return new Result(false);
		}
			
		return new Result(true);	
	}
	
	@RequestMapping(value="/response",method=RequestMethod.DELETE)
	public Result delAppointmentInviteUser(@RequestBody UserAndAppointment postclass){
		Appointment appointment = appointmentRepository.findById(postclass.getAppointment_id());
		User user = userRepository.findById(postclass.getUser_id());	
		
		try{
			appointment.getInviteusers().remove(user);
			appointmentRepository.save(appointment);
		}
		catch(Exception e){
			return new Result(false);
		}
			
		return new Result(true);	
	}
	
//	@RequestMapping(method=RequestMethod.PUT)
//	public com.fifteentec.yoko.server.model.UserEnrollAppointment updateUserEnrollAppointment(@RequestBody UserEnrollAppointment putclass){
//		com.fifteentec.yoko.server.model.UserEnrollAppointment userenrollappointment = userenrollappointmentRepository.findByUser_idAndAppointment_id(putclass.getUser_id(), putclass.getAppointment_id());
////		try{
////			userenrollappointment.setStatus(1);
////			userenrollappointmentRepository.save(userenrollappointment);
////		}
////		catch(Exception e){
////			return new Result(false);
////		}		
////		return new Result(true);
//		userenrollappointment.setStatus(1);
//		userenrollappointmentRepository.save(userenrollappointment);
//		return userenrollappointment;
//	}

}

class UserAndAppointment{
	Long user_id;
	Long appointment_id;
	public Long getUser_id() {
		return user_id;
	}
	public Long getAppointment_id() {
		return appointment_id;
	}
		
}