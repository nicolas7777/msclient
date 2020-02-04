package com.microservicio.app;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.microservicio.app.document.Client;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsclientApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private WebTestClient webTestClient;
	
	
//	@Autowired
//	private IClientService iClientService;
	
	//CRUD:save
	@Test
	void saveBank() {
		//String sUUID = UUID.randomUUID().toString();
		Client client = new Client(null,"Juan","Jaramillo","PERSONAL","12345678",new Date(),"NEW");
		webTestClient.post()
		.uri("/client/createclient")
		.body(Mono.just(client), Client.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Client.class)
		.consumeWith(response -> {
			Client clientres = response.getResponseBody();
			System.out.println("[Cliente registrado] " + client);
			//Assertions.assertThat(clientres.getId()).isNotNull().isEqualTo(sUUID);			
			Assertions.assertThat(clientres.getFirstname()).isNotNull().isEqualTo("Juan");
			Assertions.assertThat(clientres.getLastname()).isNotNull().isEqualTo("Jaramillo");
			Assertions.assertThat(clientres.getKindclient()).isNotNull().isEqualTo("PERSONAL");
			Assertions.assertThat(clientres.getDocumentnumber()).isNotNull().isEqualTo("12345678");
			
		});
			
		   Client client2 = new Client(null,"Mario","Mamani","BUSINESS","87654321",new Date(),"NEW");
			webTestClient.post()
			.uri("/client/createclient")
			.body(Mono.just(client2), Client.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Client.class)
			.consumeWith(response -> {
				Client clientres = response.getResponseBody();
				System.out.println("[Cliente registrado] " + client2);
				//Assertions.assertThat(clientres.getId()).isNotNull().isEqualTo(sUUID);			
				Assertions.assertThat(clientres.getFirstname()).isNotNull().isEqualTo("Mario");
				Assertions.assertThat(clientres.getLastname()).isNotNull().isEqualTo("Mamani");
				Assertions.assertThat(clientres.getKindclient()).isNotNull().isEqualTo("BUSINESS");
				Assertions.assertThat(clientres.getDocumentnumber()).isNotNull().isEqualTo("87654321");			
		});
	}

}
