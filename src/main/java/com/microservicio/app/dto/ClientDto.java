package com.microservicio.app.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data 
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDto {	
	@Id
	private String id;
	@NotNull
	@Size(min = 3, max = 100)
	private String firstname;
	@NotNull
	@Size(min = 3, max = 100)
	private String lastname;
	@NotNull
	@Size(min = 3, max = 50)
	private String kindclient;
	@NotNull
	@Size(min = 8, max = 11)
	private String documentnumber;	
	private Date date;
	@NotNull
	@Size(min = 8, max = 11)
	private String status;
	public AccountDto account;
}
