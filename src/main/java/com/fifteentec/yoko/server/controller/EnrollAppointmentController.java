package com.fifteentec.yoko.server.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.AppointmentRepository;
import com.fifteentec.yoko.server.repository.UserAppointmentRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/enrollappointment")  
@EnableAutoConfiguration

public class EnrollAppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserAppointmentRelationRepository userAppointmentRelationRepository;
	
	
	@RequestMapping(value="byuser/{user_id}",method=RequestMethod.GET)
	public Set<Appointment> getAppointmentByUser(@PathVariable("user_id") Long user_id){
		User user = userRepository.findById(user_id);
		Set<Appointment> appointments= user.findAppointmentsByUserAppointmentRelations();
		return appointments;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addUserAppointmentRelation(@RequestBody UserAndAppointment postclass){
		UserAppointmentRelation userAppointmentRelation = new UserAppointmentRelation();
		
		try{
			userAppointmentRelation.setUser(userRepository.findById(postclass.getUser_id()));
			userAppointmentRelation.setAppointment(appointmentRepository.findById(postclass.getAppointment_id()));
			userAppointmentRelationRepository.save(userAppointmentRelation);
		}
		catch(Exception e){
			return new Result(false);
		}
			
		return new Result(true);	
	}
	
	@RequestMapping(value="/response",method=RequestMethod.PUT)
	public Result updateUserAppointmentRelation(@RequestBody UserAndAppointment postclass){
		UserAppointmentRelation userAppointmentRelation = userAppointmentRelationRepository.findByUser_idAndAppointment_id(postclass.getUser_id(), postclass.getAppointment_id());
		
		try{
			userAppointmentRelation.setStatus(1);
			userAppointmentRelationRepository.save(userAppointmentRelation);
		}
		catch(Exception e){
			return new Result(false);
		}
			
		return new Result(true);	
	}
//	
//	@RequestMapping(value="/response",method=RequestMethod.DELETE)
//	public Result delAppointmentInviteUser(@RequestBody UserAndAppointment postclass){
//		Appointment appointment = appointmentRepository.findById(postclass.getAppointment_id());
//		User user = userRepository.findById(postclass.getUser_id());	
//		
//		try{
//	//		appointment.getInviteusers().remove(user);
//			appointmentRepository.save(appointment);
//		}
//		catch(Exception e){
//			return new Result(false);
//		}
//			
//		return new Result(true);	
//	}
//	
////	@RequestMapping(method=RequestMethod.PUT)
////	public com.fifteentec.yoko.server.model.UserEnrollAppointment updateUserEnrollAppointment(@RequestBody UserEnrollAppointment putclass){
////		com.fifteentec.yoko.server.model.UserEnrollAppointment userenrollappointment = userenrollappointmentRepository.findByUser_idAndAppointment_id(putclass.getUser_id(), putclass.getAppointment_id());
//////		try{
//////			userenrollappointment.setStatus(1);
//////			userenrollappointmentRepository.save(userenrollappointment);
//////		}
//////		catch(Exception e){
//////			return new Result(false);
//////		}		
//////		return new Result(true);
////		userenrollappointment.setStatus(1);
////		userenrollappointmentRepository.save(userenrollappointment);
////		return userenrollappointment;
////	}

}

class UserAndAppointment{
	private Long user_id;
	private Long appointment_id;
	public Long getUser_id() {
		return user_id;
	}
	public Long getAppointment_id() {
		return appointment_id;
	}
		
}