package com.fifteentec.yoko.server.service;



import java.security.Principal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fifteentec.yoko.server.exception.AppointmentNotFoundException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Appointment;
import com.fifteentec.yoko.server.model.Result;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.model.UserAppointmentRelation;
import com.fifteentec.yoko.server.repository.AppointmentRepository;
import com.fifteentec.yoko.server.repository.UserAppointmentRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@Service
public class AppointmentService {
	
	private Logger logger = LoggerFactory.getLogger(AppointmentService.class);

	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	UserAppointmentRelationRepository userAppointmentRelationRepository;
	
	public Set<Appointment> getUserAllHostAppointments(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getUserAllHostAppointments] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		return user.getAppointments();
	}
	
	public Appointment getHostAppointment(String user_mobile, Long appointment_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getHostAppointment] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Appointment appointment = appointmentRepository.findById(appointment_id);
		if(appointment == null){
			logger.error("[getHostAppointment] appointment:" + appointment_id + "doesn't exist; "  );
			throw new AppointmentNotFoundException(appointment_id);
		}
		if(appointment.getUser() == user)
		{
			logger.info("[getHostAppointment] get the appointment:" + appointment_id);
			return appointment;			
		}
		else{
			logger.error("[getHostAppointment] appointment:" + appointment_id + "doesn't belong to user: " + user_mobile  );
			throw new AppointmentNotFoundException(appointment_id);
		}
	}
	
	public Boolean addHostAppointment(String user_mobile,  Appointment postclass){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addHostAppointment] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		try{
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
			appointmentRepository.save(appointment);
		}
		catch(Exception e){
			logger.error("[addHostAppointment] user:" + user_mobile + "cannot add an appointment");
			return false;
		}
		logger.info("[addHostAppointment] user:" + user_mobile + "added an appointment named: " + postclass.getName()  );
		return true;	
	}
	
	public Set<Appointment> getUserAllEnrollAppointments(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getUserAllEnrollAppointments] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Set<Appointment> appointments= user.findAppointmentsByUserAppointmentRelations();
		logger.info("[getUserAllEnrollAppointments] user:" + user_mobile +  " get enroll appointments");
		return appointments;
	}
	
	public Boolean inviteUserAppointment(String user_mobile, Long invitee_user_id, Long appointment_id){
		User user = userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getUserAllEnrollAppointments] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Appointment appointment = appointmentRepository.findById(appointment_id);
		if(appointment.getUser() != user)
			return false;
		if(userAppointmentRelationRepository.findByUser_idAndAppointment_id(invitee_user_id,appointment_id)!=null)
			return false;
		UserAppointmentRelation userAppointmentRelation = new UserAppointmentRelation();
		
		try{
			userAppointmentRelation.setUser(userRepository.findById(invitee_user_id));
			userAppointmentRelation.setAppointment(appointment);
			userAppointmentRelationRepository.save(userAppointmentRelation);
		}
		catch(Exception e){
			return false;
		}
		return true;	
	}
	
	public Boolean updateUserAppointmentRelationStatus(Long user_id, Long appointment_id, int status){
		UserAppointmentRelation userAppointmentRelation = 
				userAppointmentRelationRepository.findByUser_idAndAppointment_id(user_id, appointment_id);
		
		try{
			userAppointmentRelation.setStatus(status);
			userAppointmentRelationRepository.save(userAppointmentRelation);
		}
		catch(Exception e){
			return false;
		}
			
		return true;	
	}
	
	
}
