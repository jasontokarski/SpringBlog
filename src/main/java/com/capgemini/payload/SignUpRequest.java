package com.capgemini.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;
    
    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;
    
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    
	public SignUpRequest() {
	}
	
	

	public SignUpRequest(@NotBlank @Size(min = 4, max = 40) String firstName,
			@NotBlank @Size(min = 4, max = 40) String lastName, @NotBlank @Size(min = 3, max = 15) String username,
			@NotBlank @Size(min = 6, max = 20) String password, @NotBlank @Size(max = 40) @Email String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
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
}
