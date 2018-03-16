package com.fb.vuebasicproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
			http.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/app**").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
					.formLogin()
					    .loginPage("/login")
						.defaultSuccessUrl("/app")			
						.permitAll().and()
					.logout().permitAll();
			// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

}