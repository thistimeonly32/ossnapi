package com.cardinal.ossnapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardinal.ossnapi.dto.MessageRequestResponse;
import com.cardinal.ossnapi.dto.MessageRequestResponsePaginated;
import com.cardinal.ossnapi.dto.UserResponse;
import com.cardinal.ossnapi.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin("*")
	@ApiOperation(value = "Get List of all Users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UserResponse>> findAll() {

		return new ResponseEntity<List<UserResponse>>(userService.findAll(), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Get List of Friend by User id (PK)")
	@GetMapping(path = "friends/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UserResponse>> getUserFriends(@PathVariable Long userId) {
		return new ResponseEntity<List<UserResponse>>(userService.findAllFriends(userId), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Get user of User id (PK)")
	@GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
		return new ResponseEntity<UserResponse>(userService.findUserById(userId), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Get messages of User by to_user_id and from_user_id (fk)")
	@GetMapping(path = "messages/{toUserId}/{fromUserId}/{pageNo}/{timeStr}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MessageRequestResponsePaginated> getUserMessages(@PathVariable Long toUserId,
			@PathVariable Long fromUserId, @PathVariable Integer pageNo, @PathVariable String timeStr) {
		return new ResponseEntity<MessageRequestResponsePaginated>(userService.getMessagesByUser(toUserId, fromUserId, pageNo, timeStr),
				HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Save Message of User by to_user_id and from_user_id (fk)")
	@PostMapping(path = "save-message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MessageRequestResponse> saveUserMessage(
			@RequestBody MessageRequestResponse messageRequestResponse) {
		return new ResponseEntity<MessageRequestResponse>(userService.saveUserMessage(messageRequestResponse),
				HttpStatus.CREATED);
	}
}
