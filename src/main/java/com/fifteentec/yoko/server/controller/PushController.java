package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.PushInfo;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.service.PushService;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@EnableAutoConfiguration
public class PushController {
	
	@Autowired
	PushService pushService;
	
	@RequestMapping(value="/pushinfo",method=RequestMethod.POST)
	public User addPushInfo(Principal principal,@RequestBody PushInfo postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return pushService.setPushInfo(Account.findMobile(principal.getName()), postclass);
	}
	
	
}


