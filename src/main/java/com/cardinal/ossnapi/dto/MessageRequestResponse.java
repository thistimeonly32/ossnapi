package com.cardinal.ossnapi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MessageRequestResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long messageId;
	private UserResponse toUser;
	private UserResponse fromUser;
	private String message;
	private String creationTime;

	public MessageRequestResponse() {
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public UserResponse getToUser() {
		return toUser;
	}

	public void setToUser(UserResponse toUser) {
		this.toUser = toUser;
	}

	public UserResponse getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserResponse fromUser) {
		this.fromUser = fromUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}