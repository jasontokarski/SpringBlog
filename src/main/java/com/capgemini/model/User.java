package com.capgemini.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
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
	
	//Check if our account is enabled
	@Column(name = "active")
	@NotNull
	private int active;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

	public User() {
	}

	public User(Long id, @NotNull(message = "Please enter a first name.") @Size(min = 2, max = 25) String firstName,
			@NotNull(message = "Please enter a last name.") @Size(min = 2, max = 25) String lastName,
			@NotNull(message = "Please enter a username.") @Size(min = 3, max = 25) String username,
			@NotNull(message = "Please enter a password.") @Size(min = 3, max = 25) String password,
			@Email(message = "Invalid email format.") @NotNull(message = "Please enter an email address.") @Size(min = 3, max = 25) String email,
			@NotNull int active, Set<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.active = active;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
