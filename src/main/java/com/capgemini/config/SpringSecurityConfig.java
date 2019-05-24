package com.capgemini.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Disable cross site scripting forgery detection
		http.authorizeRequests()
			.antMatchers("/", "/login", "/registration").permitAll()
			.antMatchers("/user/**").hasAnyRole("USER")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticate")
			.permitAll()
			.and()
		.logout()
			.logoutSuccessUrl("/");
				
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.
    		jdbcAuthentication()
    		.usersByUsernameQuery("select username, password, active from users where username=?")
    		.authoritiesByUsernameQuery("select u.username, r.role from users u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.username=?")
    		.dataSource(dataSource)
    		.passwordEncoder(bCryptPasswordEncoder);
    }
}
