package com.cardinal.ossnapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.cardinal.ossnapi.dto.UserResponse;

@Controller
public class UserWSController {

	List<UserResponse> onlineUsers = new ArrayList<UserResponse>();
	

}
