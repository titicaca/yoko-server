package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/collectactivity")  
@EnableAutoConfiguration

public class CollectActivityController {
	
	@RequestMapping(method=RequestMethod.POST)
	public CollectActivity addCollectActivity(@RequestBody CollectActivity postclass){
		CollectActivity collectactivity = new CollectActivity();
		collectactivity.setUser_id(postclass.getUser_id());
		collectactivity.setActivity_id(postclass.getActivity_id());
		return collectactivity;
	}

}
