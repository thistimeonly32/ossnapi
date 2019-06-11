package com.cardinal.ossnapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cardinal.ossnapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);

	@Query(value = "select * from user where user_id != :userId", nativeQuery = true)
	List<User> findAllFriend(@Param("userId") Long userId);

}