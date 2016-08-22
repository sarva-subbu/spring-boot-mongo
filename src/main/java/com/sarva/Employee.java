package com.sarva;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;

@Data
public class Employee {

	@Id
	private String id;
	
	@Version
	private Long version;
	
	@Indexed
	private String firstName;
	
	@Indexed
	private String lastName;
	
	@Indexed(unique=true)
	private String email;
	
}
