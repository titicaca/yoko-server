package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.service.AppointmentService;
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@RequestMapping("/user/myappointment")  
@EnableAutoConfiguration
public class UserAppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@RequestMapping(value="/{appointment_id}",method=RequestMethod.GET)
	public Appointment getHostAppointment(Principal principal, @PathVariable("appointment_id") Long appointment_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return appointmentService.getHostAppointment(Account.findMobile(principal.getName()), appointment_id);
	}
	
	@RequestMapping(value="/host", method=RequestMethod.POST)
	public ResponseResult addHostAppointment(Principal principal, @RequestBody Appointment postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return appointmentService.addHostAppointment(Account.findMobile(principal.getName()), postclass);
	}
	
	@RequestMapping(value="/host/appointments",method=RequestMethod.GET)
	public String getUserAllHostAppointments(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Appointment> appointments = appointmentService.getUserAllHostAppointments(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(appointments);	
	}
	
	//TODO to be tested
	@RequestMapping(value="/host/appointments/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUserAllHostAppointmentsWithPaging(Principal principal, @PathVariable int pageno, @PathVariable int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Appointment> appointments = appointmentService.getUserAllHostAppointmentsWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(appointments);	
	}
	
	@RequestMapping(value="/enroll/appointments",method=RequestMethod.GET)
	public String getUserAllEnrollAppointments(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Appointment> appointments = appointmentService.getUserAllEnrollAppointments(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(appointments);	
	}
	
	//TODO to be tested
	@RequestMapping(value="/enroll/appointments/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUserAllEnrollAppointmentsWithPaging(Principal principal, @PathVariable int pageno, @PathVariable int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Appointment> appointments = appointmentService.getUserAllEnrollAppointmentsWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(appointments);	
	}
	
	@RequestMapping(value="/enroll/invite", method=RequestMethod.POST)
	public ResponseResult inviteUserAppointment(Principal principal , @RequestBody UserAndAppointment postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return appointmentService.inviteUserAppointment(Account.findMobile(principal.getName()), 
				postclass.getUser_id(), postclass.getAppointment_id());
	}
	
	@RequestMapping(value="/enroll/response",method=RequestMethod.PUT)
	public ResponseResult updateUserAppointmentRelationStatus(Principal principal, @RequestBody UserAndAppointment postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return appointmentService.updateUserAppointmentRelationStatus(Account.findMobile(principal.getName()), postclass.getUser_id(), 
				postclass.getAppointment_id(), postclass.getStatus());	
	}
	
}


class UserAndAppointment{
	private Long user_id;
	private Long appointment_id;
	private int status = 0;
	public int getStatus(){
		return status;
	}
	public Long getUser_id() {
		return user_id;
	}
	public Long getAppointment_id() {
		return appointment_id;
	}
		
}
