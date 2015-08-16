package com.fifteentec.yoko.server.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import com.fifteentec.yoko.server.model.PushInfo;


import org.json.*; 


@Service
public class RedisService {
	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
//    public User findUser(Long id){  
//        System.out.println("无缓存的时候调用这里");  
//        User user = userRepository.findById(id);
//        return user;
//    }  
	
	@Autowired
	private StringRedisTemplate template;
	
	public void setPushInfo(Long id, PushInfo pushInfo){
		ValueOperations<String, String> ops = this.template.opsForValue();
		JSONObject jsonObject = new JSONObject(pushInfo);
		String key = "pushinfo-userid_"+id.toString();
		
		ops.set(key, jsonObject.toString());
		
	}
	
	public PushInfo getPushInfo(Long id){
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "pushinfo-userid_"+id.toString();
		if(ops.get(key) == null) return null;
		JSONObject jsonObject = new JSONObject(ops.get(key));	
		PushInfo pushInfo = new PushInfo();
		pushInfo.setUserid(jsonObject.getLong("userid"));
		pushInfo.setChannelid(jsonObject.getLong("channelid"));
		pushInfo.setDeviceinfo(jsonObject.getString("deviceinfo"));
		return pushInfo;
	}
	
	public void setLogInTime(Long id){
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "logintime-userid_"+id.toString();		
		ops.set(key, new Timestamp(System.currentTimeMillis()).toString());
	}
	
	public Date getLogInTime(Long id){
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "logintime-userid_"+id.toString();
		if(ops.get(key) == null) return null;
		else return  Timestamp.valueOf(ops.get(key));
	}
	
	
	
 
}


