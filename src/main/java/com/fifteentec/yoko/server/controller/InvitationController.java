package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.Invitation;

@RestController  
@RequestMapping("/invitation")  
@EnableAutoConfiguration
public class InvitationController {
	
	@RequestMapping(method=RequestMethod.GET)
	public Invitation test(){
		Invitation invitation = new Invitation();
		return invitation;
	}

}
