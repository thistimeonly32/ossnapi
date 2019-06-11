package com.cardinal.ossnapi.util;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.cardinal.ossnapi.dto.MessageRequestResponse;
import com.cardinal.ossnapi.dto.UserResponse;
import com.cardinal.ossnapi.model.Message;
import com.cardinal.ossnapi.model.RealTimeUser;
import com.cardinal.ossnapi.model.User;

@Component
@Transactional
public class BeanMapperUtil extends DozerBeanMapper {

	public UserResponse mapAsUserResponse(User user) {
		UserResponse userRes = this.map(user, UserResponse.class);
		userRes.setOnline(RealTimeUser.isUserOnline(userRes.getUserId()));
		return userRes;
	}

	public List<UserResponse> mapAsUserResponseList(List<User> users) {
		List<UserResponse> userResponses = new ArrayList<>();
		if (users != null && !users.isEmpty()) {
			for (User userObj : users) {
				userResponses.add(this.mapAsUserResponse(userObj));
			}
		}
		return userResponses;
	}

	public MessageRequestResponse mapAsMessageRequestResponse(Message message) {
		MessageRequestResponse messageRequestResponse = this.map(message, MessageRequestResponse.class);
		messageRequestResponse
				.setToUser(new UserResponse(message.getToUser().getUserId(), message.getToUser().getName()));
		messageRequestResponse
				.setFromUser(new UserResponse(message.getFromUser().getUserId(), message.getFromUser().getName()));
		messageRequestResponse.setCreationTime(message.getCreationTimestamp().toString());
		return messageRequestResponse;
	}

	public List<MessageRequestResponse> mapAsMessageRequestResponseList(List<Message> messages) {
		List<MessageRequestResponse> messageReqResponses = new ArrayList<>();
		if (messages != null && !messages.isEmpty()) {
			for (Message messageObj : messages) {
				messageReqResponses.add(this.mapAsMessageRequestResponse(messageObj));
			}
		}
		return messageReqResponses;
	}
}
