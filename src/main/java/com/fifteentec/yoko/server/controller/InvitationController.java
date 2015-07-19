package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.Invitation;

@RestController  
@RequestMapping("/invitation")  
@EnableAutoConfiguration
public class InvitationController {
	
	@RequestMapping(method=RequestMethod.POST)
	public Invitation addInvitation(@RequestBody Invitation postclass){
		Invitation invitation = new Invitation();
		invitation.setAppointment_id(postclass.getAppointment_id());
		invitation.setUser_id(postclass.getUser_id());
		invitation.setFriend_id(postclass.getFriend_id());
		invitation.setType(postclass.getType());
		return invitation;
	}

}
