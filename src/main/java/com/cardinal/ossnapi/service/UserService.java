package com.cardinal.ossnapi.service;

import java.util.List;

import com.cardinal.ossnapi.dto.LoginResponse;
import com.cardinal.ossnapi.dto.MessageRequestResponse;
import com.cardinal.ossnapi.dto.MessageRequestResponsePaginated;
import com.cardinal.ossnapi.dto.UserResponse;

public interface UserService {

	public List<UserResponse> findAll();

	public LoginResponse validateUserForLogin(String email, String password);

	public List<UserResponse> findAllFriends(Long userId);

	public UserResponse findUserById(Long userId);

	public MessageRequestResponsePaginated getMessagesByUser(Long toUserId, Long fromUserId, Integer pageNo,
			String timeStr);

	public MessageRequestResponse saveUserMessage(MessageRequestResponse messageRequestResponse);

}
