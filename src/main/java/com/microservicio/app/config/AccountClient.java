package com.microservicio.app.config;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservicio.app.dto.AccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountClient.class);
	
	@Autowired
	@Qualifier("account")
	private WebClient webClient;

	public Mono<AccountDto> createAccount(AccountDto accountdto){
		//LOGGER.info("BankingProductClient");
		return webClient.post()
				.uri("/create")
				.body(BodyInserters.fromObject(accountdto))
				.retrieve()//Perform the HTTP request and retrieve the reponse body:
				.bodyToMono(AccountDto.class);//Extract the body to a Mono. By default, if the response has status code 4xx or 5xx,the Mono wil contain a WebClientException. This can be overriddenwith onStatus(Predicate, Function).
		
	}	
}
