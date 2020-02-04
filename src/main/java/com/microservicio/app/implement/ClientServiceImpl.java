package com.microservicio.app.implement;

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

import com.microservicio.app.client.AccountClient;
import com.microservicio.app.document.Client;
import com.microservicio.app.dto.AccountDto;
import com.microservicio.app.dto.ClientDto;
import com.microservicio.app.repository.ClientRepository;
import com.microservicio.app.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//para la capa de negocio, tambien para evitar el error de ClientServiceImpl
@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
    private ClientRepository clientRepository; 
	
	@Autowired
	private AccountClient accountClient;
    
    @Override
    public Mono<Client> update(String documentnumber, Client client) { 
    			return this.clientRepository
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
    		            .flatMap(this.clientRepository::save);
    }
    
    @Override
    public Mono<Client> deleteByDocumentNumber(String documentnumber) {    
    	 return this.clientRepository
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
               .flatMap(this.clientRepository::save); 
    }

    
    @Override
    public Mono<Client> createclient(Client clientdto)  {  
//    	return this.clientRepository.findByDocumentNumber(client.getDocumentnumber())    			
//		.flatMap(p->{ 
//			if(p.toString().equals("")) {
				return this.clientRepository.save(new Client (
						"CLI"+UUID.randomUUID().toString(),
						clientdto.getFirstname(),
						clientdto.getLastname(),
						clientdto.getKindclient(),
						clientdto.getDocumentnumber(),				 
						new Date(),
						"NEW"
						));
    }
    @Override
    public Mono<AccountDto> create(ClientDto clientdto) {  
//    	return this.clientRepository.findByDocumentNumber(client.getDocumentnumber())    			
//    			.flatMap(p->{ 
//    				if(p.toString().equals("")) {
	    				return this.clientRepository.save(new Client (
	    						"CLI"+UUID.randomUUID().toString(),
	    						clientdto.getFirstname(),
	    						clientdto.getLastname(),
	    						clientdto.getKindclient(),
	    						clientdto.getDocumentnumber(),				 
								new Date(),
								"NEW"
								))
	    						.flatMap(p->{
	    							clientdto.account.setIdcliente(p.getId());
	    							return this.accountClient.createAccount(clientdto.account);
	    							
	    							
	    						});  				
//    				}else {
//    					return this.clientRepository.findByDocumentNumber(client.getDocumentnumber());
//    				}
//    			}); 		
						 	
    }	

	@Override
	public Mono<Client> findByNumberDocument(String numberdocument) {
		return this.clientRepository.findByDocumentnumber(numberdocument);
	}

	@Override
	public Flux<Client> findAll() {
		return this.clientRepository.findAll();
	}	

	
	
	


	
}
