package com.fifteentec.yoko.server.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.PushInfo;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.UserFriendRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

@Service
public class PushService {
	
	private Logger logger = LoggerFactory.getLogger(PushService.class);
	
	private String apiKey = "DktSnpqB2wljcjOeIYW4f2BI";
    private String secretKey = "FLzPDIHK0MarvjdtEpzHH2OGe4KUG6kp";
	
	@Autowired
	RedisService redisService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserFriendRelationRepository userFriendRelationRepository;
	
	
	public User setPushInfo(String user_mobile, PushInfo postclass) {
		User user = userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[setPushInfo] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		try{
			redisService.setPushInfo(user.getId(),postclass);
		}
		catch(Exception e){
			return null;
		}
		logger.info("[LogIn] user: " + user_mobile);
		redisService.setLogInTime(user.getId());
		user.setCollectnumber(user.getCollectactivities().size());
		user.setEnrollnumber(user.getEnrollactivities().size());
		user.setFriendnumber(userFriendRelationRepository.findByUser_id(user.getId()).size());
		return user;
	}
	
	public ResponseResult  pushMessageSingle(String user_mobile, String description) throws PushClientException, PushServerException{
		
        /*1. 创建PushKeyPair
         *用于app的合法身份认证
         *apikey和secretKey可在应用详情中获取
         */
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[pushMessageSingle] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		
		PushInfo pushInfo = redisService.getPushInfo(user.getId());
		if(pushInfo == null) return new ResponseResult(false, "error:cannot find channelid;");
		
        PushKeyPair pair = new PushKeyPair(apiKey,secretKey);

        // 2. 创建BaiduPushClient，访问SDK接口
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. 注册YunLogHandler，获取本次请求的交互信息
        pushClient.setChannelLogHandler (new YunLogHandler () {
        	@Override
            public void onHandle (YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
          
        System.out.println("2222222    "+description);
        
        try {
        // 4. 设置请求参数，创建请求实例
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                addChannelId(pushInfo.getChannelid().toString()).
                addMsgExpires(new Integer(3600*24*7)).   //设置消息的有效时间,单位秒,默认3600*5.
                addMessageType(0).           //设置消息类型,0表示透传消息,1表示通知,默认为0.
                addMessage(description).
                addDeviceType(3);      //设置设备类型，deviceType => 1 for web, 2 for pc, 
                                       //3 for android, 4 for ios, 5 for wp.
        // 5. 执行Http请求
            PushMsgToSingleDeviceResponse response = pushClient.
                pushMsgToSingleDevice(request);
        // 6. Http请求返回值解析
       //     System.out.println("msgId: " + response.getMsgId()
         //           + ",sendTime: " + response.getSendTime());
        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            return new ResponseResult(false, e.toString());
        } catch (PushServerException e) {
        	return new ResponseResult(false, e.toString());
        }
        return new ResponseResult(true);
	}
	
	
	
}


