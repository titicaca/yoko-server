package com.fifteentec.yoko.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
 
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.UserRepository;


@Service
public class RedisService {
	
	@Autowired
	UserRepository userRepository;
	
	@Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
    public User findUser(Long id){  
        System.out.println("无缓存的时候调用这里");  
        User user = userRepository.findById(id);
        return user;
    }  
 
}


