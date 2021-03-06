package com.fifteentec.yoko.server.service;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.AppointmentNotFoundException;
import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Appointment;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.model.UserAppointmentRelation;
import com.fifteentec.yoko.server.repository.AppointmentRepository;
import com.fifteentec.yoko.server.repository.UserAppointmentRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

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
	
	//TODO to be tested
	public List<Appointment> getUserAllHostAppointmentsWithPaging(String user_mobile, int pageno, int pagesize){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getUserAllHostAppointments] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize, s);
		return appointmentRepository.findByUser(user, pageable).getContent();
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
	
	public ResponseResult addHostAppointment(String user_mobile,  Appointment postclass){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addHostAppointment] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Appointment appointment = new Appointment();
		try{
			appointment.setName(postclass.getName());
			appointment.setTimebegin(postclass.getTimebegin());
			appointment.setTimeend(postclass.getTimeend());
			appointment.setLocation(postclass.getLocation());
			appointment.setIntroduction(postclass.getIntroduction());
			appointment.setPeopleall(postclass.getPeopleall());
			appointment.setPicturelink(postclass.getPicturelink());
			appointment.setDetaillink(postclass.getDetaillink());
			appointment.setUser(user);
			appointment = appointmentRepository.save(appointment);
		}
		catch(Exception e){
			logger.error("[addHostAppointment] user:" + user_mobile + "cannot add an appointment");
			return new ResponseResult(false, e.toString());
		}
		logger.info("[addHostAppointment] user:" + user_mobile + "added an appointment named: " + postclass.getName()  );
		String msg = String.format("{\"postid\" : %s｝", appointment.getId());
		return new ResponseResult(true, msg);	
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
	
	//TODO to be tested
	public List<Appointment> getUserAllEnrollAppointmentsWithPaging(String user_mobile, int pageno, int pagesize){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getUserAllEnrollAppointments] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		List<UserAppointmentRelation> relations = userAppointmentRelationRepository.findByUser(user, pageable).getContent();
		List<Appointment> appointments= new ArrayList<Appointment>();
		for (UserAppointmentRelation r : relations){
			appointments.add(r.getAppointment());
		}
		logger.info("[getUserAllEnrollAppointments] user:" + user_mobile +  " get enroll appointments");
		return appointments;
	}
	
	public ResponseResult inviteUserAppointment(String user_mobile, Long invitee_user_id, Long appointment_id){
		User user = userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getUserAllEnrollAppointments] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Appointment appointment = appointmentRepository.findById(appointment_id);
		if(appointment.getUser() != user)
			return new ResponseResult(false, "user is not the owner");
		if(userAppointmentRelationRepository.findByUser_idAndAppointment_id(invitee_user_id,appointment_id)!=null)
			return new ResponseResult(false, "user has already been invited.");
		
		UserAppointmentRelation userAppointmentRelation = new UserAppointmentRelation();
		
		try{
			userAppointmentRelation.setUser(userRepository.findById(invitee_user_id));
			userAppointmentRelation.setAppointment(appointment);
			userAppointmentRelationRepository.save(userAppointmentRelation);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);	
	}
	
	public ResponseResult updateUserAppointmentRelationStatus(String user_mobile, Long user_id, Long appointment_id, int status){
		User user = userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[updateUserAppointmentRelationStatus] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
//		if(user.getId() != user_id){
//			logger.error("[updateUserAppointmentRelationStatus] user:" + user_mobile + "has no permission; "  );
//			throw new PermissionErrorException();
//		}
		UserAppointmentRelation userAppointmentRelation = 
				userAppointmentRelationRepository.findByUser_idAndAppointment_id(user_id, appointment_id);
		
		try{
			userAppointmentRelation.setStatus(status);
			userAppointmentRelationRepository.save(userAppointmentRelation);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
			
		return new ResponseResult(true);	
	}
	
	
}
