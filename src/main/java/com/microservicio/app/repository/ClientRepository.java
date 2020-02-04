package com.microservicio.app.repository;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservicio.app.document.Client;

import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveMongoRepository<Client,String> {
	
	//@Query("{ 'documentnumber' : ?0 }")
	Mono<Client> findByDocumentnumber(String documentnumber);
}
