package com.cardinal.ossnapi;

import java.security.Principal;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.cardinal.ossnapi.model.RealTimeUser;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ossnapi-websocket").setHandshakeHandler(new HandshakeHandler()).setAllowedOrigins("*")
				.withSockJS();
	}

	@EventListener
	public void onSocketConnected(SessionConnectedEvent event) {

		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		System.out.println("[Connected] " + sha.getSessionId());
		System.out.println("connected: socket-id : " + event.getUser().getName());
		RealTimeUser.addUser(Long.valueOf(event.getUser().getName()));
		System.out.println("User count: " + RealTimeUser.getOnlineUsers().size());
	}

	@EventListener
	public void onSocketDisconnected(SessionDisconnectEvent event) {

		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		System.out.println("[Disonnected] " + sha.getSessionId());
		System.out.println("disconnected: socket-id : " + event.getUser().getName());
		RealTimeUser.removeUser(Long.valueOf(event.getUser().getName()));
		System.out.println("User count: " + RealTimeUser.getOnlineUsers().size());
	}

//	.setHandshakeHandler(new HandshakeHandler())
//	.setAllowedOrigins("*")

	private class HandshakeHandler extends DefaultHandshakeHandler {

		@Override
		protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
				Map<String, Object> attributes) {
			System.out.println(request.getURI().getQuery().split("=")[1]);
			return new Principal() {
				@Override
				public String getName() {
//					return "ossn-user-peer-id-" + request.getURI().getQuery().split("=")[1];
					return request.getURI().getQuery().split("=")[1];
				}
			};
		}

	}

}
