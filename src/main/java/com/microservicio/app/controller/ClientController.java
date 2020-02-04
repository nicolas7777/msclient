package com.microservicio.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow.Publisher;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.app.document.Client;
import com.microservicio.app.dto.AccountDto;
import com.microservicio.app.dto.ClientDto;
import com.microservicio.app.service.IClientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE) //produce automatically converts List collection to json
public class ClientController {	
	
	private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;	
	//inyeccion de dependencia	
	@Autowired
	private IClientService clientService;   
	
	@PostMapping("/createclient")
	@ApiOperation(value = "CRUD", notes="")
	Mono<Client> create(@RequestBody Client clientdto) {
		return this.clientService.createclient(clientdto);
		
	}
	
	@PostMapping("/create")
	@ApiOperation(value = "CRUD", notes="")
	Mono<AccountDto> create(@RequestBody ClientDto clientdto) {
		return this.clientService.create(clientdto);
		
	}
	
	@DeleteMapping("/deletebydocumentnumber/{documentnumber}")
	@ApiOperation(value = "CRUD (only the status change)", notes="")	
	Mono<Client> deleteByDocumentNumber(@PathVariable String documentnumber) {
		return  this.clientService.deleteByDocumentNumber(documentnumber);
	}	
	
	
	@PutMapping("/update/{documentnumber}")
	@ApiOperation(value = "CRUD (id and document number doesn`t change)", notes="")
	Mono<Client> updateById(@PathVariable String documentnumber, @RequestBody Client client) {
		return  this.clientService.update(documentnumber,client);
		
	}	
	
	@GetMapping("/availableproductbalance/{documentnumber}")
	@ApiOperation(value = "AVAILABLE PRODUCT BALANCE ", notes="")	
	Mono<Client> availableproductbalance(@PathVariable String documentnumber){
		//LOGGER.info("AccountController");
		return this.clientService.findByNumberDocument(documentnumber);
	}
	
	@GetMapping("/findByDocumentNumber/{documentnumber}")
	@ApiOperation(value = "*.* ", notes="")	
	Mono<Client> findByDocumentNumber(@PathVariable String documentnumber){
		//LOGGER.info("AccountController");
		return this.clientService.findByNumberDocument(documentnumber);
	}
	
	@GetMapping("/findall")
	@ApiOperation(value = "findAll", notes="")	
	Flux<Client> findall(){
		//LOGGER.info("TransactionController");
		return this.clientService.findAll();
	}
	
}


