package com.microservicio.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.microservicio.app.document.Client;
import com.microservicio.app.dto.AccountDto;
import com.microservicio.app.dto.ClientDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    public Mono<Client> update(String numberdocument, Client client);
    public Mono<Client> deleteByDocumentNumber(String documentnumber);
    public Mono<Client> createclient(Client clientdto) ;
    public Mono<AccountDto> create(ClientDto clientdto) ;    
    public Mono<Client> findByNumberDocument(String numberdocument) ;
    public Flux<Client> findAll() ;
    public Mono<Client> findById(String id);
    
    
}
