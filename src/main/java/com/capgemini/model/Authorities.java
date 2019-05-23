package com.capgemini.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authorities")
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "authority")
	@NotNull
	private String authority;
	
	@Column(name = "username")
	@NotNull
	private String username;
}
