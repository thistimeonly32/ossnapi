package com.cardinal.ossnapi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cardinal.ossnapi.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query(value = "select * from message where ((to_user_id = :toUserId and from_user_id = :fromUserId) "
			+ "or (to_user_id = :fromUserId and from_user_id = :toUserId)) and creation_timestamp <= :timeStr "
			+ "order by creation_timestamp desc", nativeQuery = true)
	List<Message> findMessageByUser(@Param(value = "toUserId") Long toUserId,
			@Param(value = "fromUserId") Long fromUserId, @Param(value = "timeStr") String timeStr, Pageable pageable);
//	List<Message> findMessageByUser(@Param(value = "toUserId") Long toUserId,
//			@Param(value = "fromUserId") Long fromUserId);

}