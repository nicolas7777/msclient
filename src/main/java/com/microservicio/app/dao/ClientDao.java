package com.microservicio.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservicio.app.document.Client;

import reactor.core.publisher.Mono;

public interface ClientDao extends ReactiveMongoRepository<Client,String> {
	
	//@Query("{ 'documentnumber' : ?0 }")
	Mono<Client> findByDocumentnumber(String documentnumber);
	

}
