package com.microservicio.app.service;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservicio.app.config.AccountClient;
import com.microservicio.app.dao.ClientDao;
import com.microservicio.app.document.Client;
import com.microservicio.app.dto.AccountDto;
import com.microservicio.app.dto.ClientDto;
import com.mongodb.async.client.Observable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//para la capa de negocio, tambien para evitar el error de ClientServiceImpl
@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
    private ClientDao clientDao; 
	
	@Autowired
	private AccountClient accountClient;
    
    @Override
    public Mono<Client> update(String documentnumber, Client client) { 
    			return this.clientDao
    		            .findByDocumentnumber(documentnumber)
    		            .map(p -> new Client(  
    		            p.getId(),
    		            client.getFirstname(),
	            		client.getLastname(),
	            		client.getKindclient(),
	            		client.getDocumentnumber(),
	            		client.getDate(),
	            		client.getStatus()
	            		))
    		            .flatMap(this.clientDao::save);
    }
    
    @Override
    public Mono<Client> deleteByDocumentNumber(String documentnumber) {    
    	 return this.clientDao
               .findByDocumentnumber(documentnumber)
               .map(p-> new Client(
            		   		p.getId(),
			   				p.getFirstname(),
			   				p.getLastname(),
			   				p.getKindclient(),
			   				p.getDocumentnumber(),				 
			   				p.getDate(),
			               	"DELETED"			               	
			   				)
            		   )
               .flatMap(this.clientDao::save); 
    }

    
    @Override
    public Mono<Client> createclient(Client clientdto)  {  
				return this.clientDao.save(new Client (
						"CLI"+UUID.randomUUID().toString(),
						clientdto.getFirstname(),
						clientdto.getLastname(),
						clientdto.getKindclient(),
						clientdto.getDocumentnumber(),				 
						new Date(),
						"NEW"
						));
    }
    //Este metodo permite verificar si existe un cliente, si existe crea solo la cuenta 
    //si no existe lo crea y crea la cuenta.
    @Override
    public Mono<AccountDto> create(ClientDto clientdto) {  
    	//Se tiene que inicializar la variable cliente porque te trae null
    	Mono<Client> client = this.clientDao.findByDocumentnumber(clientdto.getDocumentnumber());
    	
    	return client.defaultIfEmpty(new Client())
    			.flatMap(p->{
    				
    				if(p.getDocumentnumber() == null) {
    					return this.clientDao.save(new Client (
	    						"CLI"+UUID.randomUUID().toString(),
	    						clientdto.getFirstname(),
	    						clientdto.getLastname(),
	    						clientdto.getKindclient(),
	    						clientdto.getDocumentnumber(),				 
								new Date(),
								"NEW"
								))
	    						.flatMap(q->{
	    							clientdto.account.setIdclient(q.getId());
	    							return this.accountClient.createAccount(clientdto.account);							
	    						});  								
    				}else {  					
    					clientdto.account.setIdclient(p.getId());
						return this.accountClient.createAccount(clientdto.account);
							
    				}
    			});
            		
						 	
    }	

	@Override
	public Mono<Client> findByNumberDocument(String numberdocument) {
		return this.clientDao.findByDocumentnumber(numberdocument);
	}

	@Override
	public Flux<Client> findAll() {
		return this.clientDao.findAll();
	}

	@Override
	public Mono<Client> findById(String id) {
		return this.clientDao.findById(id);
	}	

	
	
	


	
}
