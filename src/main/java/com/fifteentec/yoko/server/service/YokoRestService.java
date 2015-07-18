package com.fifteentec.yoko.server.service;

import org.springframework.boot.SpringApplication;
import com.fifteentec.yoko.server.*;
import com.fifteentec.yoko.server.controller.*;


public class YokoRestService {
	public static void main(String[] args) throws Exception {
   	 Object controller[] = new Object[]{
   			 Application.class,
   			 UserController.class,
   			 UserMoreInfoController.class, 
   			 ActivityGroupController.class,
   			 SponsorController.class,
   			 EnrollGroupController.class,
   			 InvitationController.class,
   			 ActivityController.class
   			 };
   	 SpringApplication.run(controller, args);
   }

}
