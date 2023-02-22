package com.misiontic.ordenesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.misiontic.ordenesapi.security.RestAuthenticationEntryPoint;
import com.misiontic.ordenesapi.security.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public TokenAuthenticationFilter createTokenAutenticationFilter() {
		return new TokenAuthenticationFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.cors()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
		.csrf()
			.disable()
		.formLogin()
			.disable()
		.httpBasic()
			.disable()
		.exceptionHandling()
			.authenticationEntryPoint(new RestAuthenticationEntryPoint())
			.and()
		.authorizeRequests()
			.antMatchers(
					"/login",
					"/signup",
					"/perfiles"
					)
				.permitAll() 
			.anyRequest()
				.authenticated();
		
		http.addFilterBefore(createTokenAutenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}

}
