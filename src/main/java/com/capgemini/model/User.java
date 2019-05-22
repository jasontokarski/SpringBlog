package com.capgemini.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "first_name")
	@NotNull(message = "Please enter a first name.")
	@Size(min = 2, max = 25)
	private String firstName;
	
	@Column(name = "last_name")
	@NotNull(message = "Please enter a last name.")
	@Size(min = 2, max = 25)
	private String lastName;
	
	@Column(name = "username", unique = true)
	@NotNull(message = "Please enter a username.")
	@Size(min = 3, max = 25)
	private String username;
	
	@Column(name = "password")
	@NotNull(message = "Please enter a password.")
	@Size(min = 3, max = 25)
	private String password;
	
	@Column(name = "email", unique = true)
	@Email(message="Invalid email format.")
	@NotNull(message = "Please enter an email address.")
	@Size(min = 3, max = 25)
	private String email;
}
