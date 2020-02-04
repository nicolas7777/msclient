package com.microservicio.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	@Bean
	@Qualifier("account")
	public WebClient registrarWebClient() {
		//local
		return WebClient.create("http://localhost:8890/account");
		//remoto
//		return WebClient.create("http://msaccount:8890/account");
	}

}