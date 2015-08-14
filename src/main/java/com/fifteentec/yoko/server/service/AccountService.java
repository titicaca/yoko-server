package com.fifteentec.yoko.server.service;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.OrganizationNotFoundException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.SignUpInfo;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.AccountRepository;
import com.fifteentec.yoko.server.repository.OrganizationRepository;
import com.fifteentec.yoko.server.repository.UserFriendRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

@Service
public class AccountService {
	
private Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private UserFriendRelationRepository userFriendRelationRepository;
	@Autowired
	RedisService redisService;
	
    public ResponseResult addUserAccount(SignUpInfo postclass) {  
		Account account = new Account();
		account.setUsername(postclass.getRole_mobile());
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	logger.error("[addUserAccount] accountRepository cannot save user: " + account.getUsername() + "; " + e.getMessage());
        	return new ResponseResult(false, e.toString());
        }
        User user = new User();
        user.setNickname(postclass.getName());
        user.setMobile(Account.findMobile(postclass.getRole_mobile()));
        try{
        	userRepository.save(user);
        }
        catch(Exception e){
        	logger.error("[addUserAccount] userRepository cannot save user: " + user.getMobile() + "; " + e.getMessage());
        	return new ResponseResult(false, e.toString());
        }
        logger.info("[addUserAccount] userRepository saved user: " + user.getMobile() );
        return new ResponseResult(true);
	}
	
    public ResponseResult updateUserAccount(SignUpInfo postclass) {  
		Account account = accountRepository.findByUsername(postclass.getRole_mobile());
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	logger.error("[updateUserAccount] accountRepository cannot save account: " + account.getUsername() + "; " + e.getMessage());
        	return new ResponseResult(false, e.toString());
        }
        logger.info("[updateUserAccount] accountRepository saved account: " + account.getUsername() );
        return new ResponseResult(true);
	}
	
    public ResponseResult addSponsorAccount(SignUpInfo postclass) {  
		Account account = new Account();
		account.setUsername(postclass.getRole_mobile());
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	logger.error("[addSponsorAccount] accountRepository cannot save account: " + account.getUsername() + "; " + e.getMessage());
        	return new ResponseResult(false, e.toString());
        }
        Organization organization = new Organization();
        organization.setRealname(postclass.getName());
        organization.setMobile(Account.findMobile(postclass.getRole_mobile()));
        try{
        	organizationRepository.save(organization);
        }
        catch(Exception e){
        	logger.error("[addSponsorAccount] organizationRepository cannot save org: " + organization.getMobile() + "; " + e.getMessage());
        	return new ResponseResult(false, e.toString());
        }
    	logger.info("[addSponsorAccount] organizationRepository saved org: " + organization.getMobile());
    	return new ResponseResult(true);
	}
	
    public ResponseResult updateSponsorAccount(SignUpInfo postclass) {  
		Account account = accountRepository.findByUsername(postclass.getRole_mobile());
		if (account == null){
			logger.error("[getUser] user: " + postclass.getRole_mobile() + "doesn't exist.");
	//TODO		
			throw new UserNotFoundException(postclass.getRole_mobile());
		}
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	logger.error("[updateSponsorAccount] accountRepository cannot save account: " + account.getUsername() + "; " + e.getMessage());
        	return new ResponseResult(false, e.toString());
        }
    	logger.info("[updateSponsorAccount] accountRepository saved account: " + account.getUsername());
    	return new ResponseResult(true);
	}
    
    
	public User getUser(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUser] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		logger.info("[getUser] get user: " + user_mobile);
		user.setLogintime(new Date());
		try{
			userRepository.save(user);
		}
		catch(Exception e){
			
		}
		user.setCollectnumber(user.getCollectactivities().size());
		user.setEnrollnumber(user.getEnrollactivities().size());
		user.setFriendnumber(userFriendRelationRepository.findByUser_id(user.getId()).size());
		return user;
	}
	
	public ResponseResult updateUserInfo(String user_mobile, User postclass){
	
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[updateUser] user: " + user_mobile + "doesn't exist.");
//			throw new UserNotFoundException(principal.getName());
			return new ResponseResult(false, "[updateUser] user: " + user_mobile + "doesn't exist.");
		}
		try{
			user.setNickname(postclass.getNickname());
			user.setMobile(postclass.getMobile());
			user.setSex(postclass.getSex());
			user.setEmail(postclass.getEmail());
			user.setQq(postclass.getQq());
			user.setWechat(postclass.getWechat());
			user.setWeibo(postclass.getWeibo());
			user.setPicturelink(postclass.getPicturelink());	
			userRepository.save(user);
		}
		catch(Exception e){
			logger.error("[updateUser] user: " + user_mobile + "cannot be saved by UserRepository.");
			return new ResponseResult(false, e.toString());
		}
		
		logger.info("[updateUser] user: " + user_mobile + "updated.");
		return new ResponseResult(true);
	}
	
	
	public Organization getOrganization(String org_mobile){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[getOrganization] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		logger.info("[getOrganization] get organization: " +org_mobile);
		return organization;
	}
	
	public ResponseResult updateOrganizationInfo(String org_mobile, Organization postclass){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[updateOrganization] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		try{
			organization.setName(postclass.getName());
			organization.setType(postclass.getType());
			organization.setMobile(postclass.getMobile());
			organization.setPicturelink(postclass.getPicturelink());
			organization.setIntroduction(postclass.getIntroduction());
			organization.setRealname(postclass.getRealname());
			organization.setCard(postclass.getCard());
			organization.setAddress(postclass.getAddress());
			organization.setPhotolink(postclass.getPhotolink());
			organizationRepository.save(organization);
		}
		catch(Exception e){
			logger.error("[updateOrganization] organization: " + org_mobile + "cannot be saved by OrganizationRepository.");
			return new ResponseResult(false, e.toString());
		}
		
		logger.info("[updateOrganization] organization: " + org_mobile + "updated.");
		return new ResponseResult(true);
	}

}
