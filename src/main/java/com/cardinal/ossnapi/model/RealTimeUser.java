package com.cardinal.ossnapi.model;

import java.util.ArrayList;
import java.util.List;

public class RealTimeUser {

	private static List<Long> onlineUsers = new ArrayList<>();

	public static List<Long> getOnlineUsers() {
		return onlineUsers;
	}

	public static void setOnlineUsers(List<Long> onlineUsers) {
		RealTimeUser.onlineUsers = onlineUsers;
	}

	public static void addUser(Long userId) {
		if (!RealTimeUser.isUserOnline(userId)) {
			RealTimeUser.onlineUsers.add(userId);
		}
	}

	public static void removeUser(Long userId) {
		RealTimeUser.onlineUsers.remove(userId);
	}

	public static Boolean isUserOnline(Long userId) {
		return RealTimeUser.onlineUsers.contains(userId);
	}

}
