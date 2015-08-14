package com.fifteentec.yoko.server.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.PushInfo;
import com.fifteentec.yoko.server.model.SignUpInfo;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

@Service
public class PushService {
	
	private Logger logger = LoggerFactory.getLogger(PushService.class);
	
	@Autowired
	RedisService redisService;
	@Autowired
	UserRepository userRepository;
	
	
	
	public ResponseResult setPushInfo(String user_mobile, PushInfo postclass) {
		User user = userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addPushInfo] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		try{
			redisService.setPushInfo(user.getId(),postclass);
		}
		catch(Exception e){
			return new ResponseResult(false);
		}
		return new ResponseResult(true);
		
	}
	
	
}
