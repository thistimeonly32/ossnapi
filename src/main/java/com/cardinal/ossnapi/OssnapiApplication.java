package com.cardinal.ossnapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class OssnapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OssnapiApplication.class, args);
	}

}
