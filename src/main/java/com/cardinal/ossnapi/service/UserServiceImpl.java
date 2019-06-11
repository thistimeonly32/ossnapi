package com.cardinal.ossnapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardinal.ossnapi.dto.LoginResponse;
import com.cardinal.ossnapi.dto.MessageRequestResponse;
import com.cardinal.ossnapi.dto.UserResponse;
import com.cardinal.ossnapi.model.Message;
import com.cardinal.ossnapi.model.User;
import com.cardinal.ossnapi.repository.MessageRepository;
import com.cardinal.ossnapi.repository.UserRepository;
import com.cardinal.ossnapi.util.BeanMapperUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	BeanMapperUtil beanMapperUtil;

	@Override
	public List<UserResponse> findAll() {
		return beanMapperUtil.mapAsUserResponseList(userRepository.findAll());
	}

	@Override
	public LoginResponse validateUserForLogin(String email, String password) {
		LoginResponse loginResponse;
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user != null) {
			loginResponse = beanMapperUtil.map(user, LoginResponse.class);
			loginResponse.setIsUserValid(true);
		} else {
			loginResponse = new LoginResponse();
			loginResponse.setIsUserValid(false);
		}
		return loginResponse;
	}

	@Override
	public List<UserResponse> findAllFriends(Long userId) {
		List<User> users = userRepository.findAllFriend(userId);
		return beanMapperUtil.mapAsUserResponseList(users);
	}

	@Override
	public UserResponse findUserById(Long userId) {
		return beanMapperUtil.mapAsUserResponse(
				userRepository.findById(userId) != null ? userRepository.findById(userId).get() : null);
	}

	@Override
	public List<MessageRequestResponse> getMessagesByUser(Long toUserId, Long fromUserId) {
		return beanMapperUtil
				.mapAsMessageRequestResponseList(messageRepository.findMessageByUser(toUserId, fromUserId));
	}

	@Override
	public MessageRequestResponse saveUserMessage(MessageRequestResponse messageRequestResponse) {
		Message message = new Message();
		message.setFromUser(userRepository.getOne(messageRequestResponse.getFromUser().getUserId()));
		message.setToUser(userRepository.getOne(messageRequestResponse.getToUser().getUserId()));
		message.setMessage(messageRequestResponse.getMessage());
		messageRepository.save(message);
		return beanMapperUtil.mapAsMessageRequestResponse(message);
	}

}
