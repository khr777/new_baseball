package com.worimodoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
// @MapperScan("com.worimodoo.baseball.dao")
public class NewBaseballApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewBaseballApplication.class, args);
	}
	
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
