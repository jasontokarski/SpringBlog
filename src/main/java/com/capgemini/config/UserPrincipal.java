package com.capgemini.config;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.capgemini.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String email;
	private int active;

	private Collection<? extends GrantedAuthority> authorities;

	
	public UserPrincipal() {
	}


	public UserPrincipal(Long id, String firstName, String lastName, String username, String password, String email,
			int active, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.active = active;
		this.authorities = authorities;
	}


	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
			new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());
		
		return new UserPrincipal(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getActive(),
				authorities
		);
	}


	public Long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getEmail() {
		return email;
	}


	public int getActive() {
		return active;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
