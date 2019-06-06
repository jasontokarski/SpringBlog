package com.capgemini.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.security.CustomUserDetailsService;
import com.capgemini.security.JwtAuthenticationEntryPoint;
import com.capgemini.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//UserDetailsService is used to grab user data from the database
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	//Implements AuthenticationEntryPoint interface, this will return 401 unauthorized to any client
	//missing proper authentication
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	//This will check the authentication token in the Authentication header of all requests.
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
	
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Autowired
    private DataSource dataSource;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
	        .cors()
	            .and()
	        .csrf()
	            .disable()
	        .exceptionHandling()
	            .authenticationEntryPoint(unauthorizedHandler)
	            .and()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	        .authorizeRequests()
	            .antMatchers("/",
	                "/favicon.ico",
	                "/**/*.png",
	                "/**/*.gif",
	                "/**/*.svg",
	                "/**/*.jpg",
	                "/**/*.html",
	                "/**/*.css",
	                "/**/*.js")
	                .permitAll()
	            .antMatchers("/api/auth/**")
	                .permitAll()
	            .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
	                .permitAll()
	            .antMatchers(HttpMethod.GET, "/api/users/**")
	                .permitAll()
	            .anyRequest()
	                .authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);



//		//Disable cross site scripting forgery detection
//		http.authorizeRequests()
//			.antMatchers("/", "/login", "/registration").permitAll()
//			.antMatchers("/user/**").hasAnyRole("USER")
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.loginPage("/login")
//			.loginProcessingUrl("/authenticate")
//			.permitAll()
//			.and()
//		.logout()
//			.logoutSuccessUrl("/");
				
	}
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.
//    		jdbcAuthentication()
//    		.usersByUsernameQuery("select username, password, active from users where username=?")
//    		.authoritiesByUsernameQuery("select u.username, r.role from users u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.username=?")
//    		.dataSource(dataSource)
//    		.passwordEncoder(passwordEncoder());
//    }
}
