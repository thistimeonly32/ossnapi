package com.cardinal.ossnapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardinal.ossnapi.dto.LoginResponse;
import com.cardinal.ossnapi.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "validate the user for login")
	@PostMapping(path = "/{email}/{password}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<LoginResponse> findAll(@PathVariable String email, @PathVariable String password) {
		return new ResponseEntity<LoginResponse>(userService.validateUserForLogin(email, password), HttpStatus.OK);
	}

}
