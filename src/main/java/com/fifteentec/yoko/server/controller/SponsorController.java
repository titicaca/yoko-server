package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.Sponsor;

@RestController  
@RequestMapping("/sponsor")  
@EnableAutoConfiguration
public class SponsorController {
	
	@RequestMapping(method=RequestMethod.POST)
	public Sponsor addSponsor(@RequestBody Sponsor postclass){
		Sponsor sponsor = new Sponsor();
		sponsor.setName(postclass.getName());
		sponsor.setCard(postclass.getCard());
		sponsor.setStatus(1);
		return sponsor;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Sponsor updateSponsor(@RequestBody Sponsor putclass){
		Sponsor sponsor = new Sponsor();
		sponsor.setName(putclass.getName());
		sponsor.setCard(putclass.getCard());
		return sponsor;
	}

}
