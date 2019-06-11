package com.cardinal.ossnapi.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cardinal.ossnapi.repository.UserRepository;

@Component
@Transactional
public class EntityFinder {

	@Autowired
	UserRepository userRepository;


}
