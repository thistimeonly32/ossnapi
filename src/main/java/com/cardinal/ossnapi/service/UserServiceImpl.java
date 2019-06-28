package com.cardinal.ossnapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cardinal.ossnapi.dto.LoginResponse;
import com.cardinal.ossnapi.dto.MessageRequestResponse;
import com.cardinal.ossnapi.dto.MessageRequestResponsePaginated;
import com.cardinal.ossnapi.dto.UserResponse;
import com.cardinal.ossnapi.model.Message;
import com.cardinal.ossnapi.model.User;
import com.cardinal.ossnapi.repository.MessageRepository;
import com.cardinal.ossnapi.repository.UserRepository;
import com.cardinal.ossnapi.util.BeanMapperUtil;
import com.cardinal.ossnapi.util.OSSNConstant;

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
	public MessageRequestResponsePaginated getMessagesByUser(Long toUserId, Long fromUserId, Integer pageNo,
			String timeStr) {
		Pageable pageable = PageRequest.of(pageNo, OSSNConstant.USER_MESSAGE_PAGE_SIZE);
		List<MessageRequestResponse> messageRequestResponse = beanMapperUtil
				.mapAsMessageRequestResponseList(messageRepository.findMessageByUser(toUserId, fromUserId, timeStr, pageable));
		MessageRequestResponsePaginated messageRequestResponsePaginated = new MessageRequestResponsePaginated(
				pageNo, timeStr, messageRequestResponse);
		return messageRequestResponsePaginated;

//		return beanMapperUtil
//				.mapAsMessageRequestResponseList(messageRepository.findMessageByUser(toUserId, fromUserId));
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
