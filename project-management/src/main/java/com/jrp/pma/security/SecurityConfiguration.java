package com.jrp.pma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Define authentication mechanism.
		// Define new user name and password. The default user name is "user".
		auth.inMemoryAuthentication().withUser("myuser").password("pass").roles("USER").and().withUser("zhaoyin")
				.password("pass2").roles("USER").and().withUser("managerUser").password("pass3").roles("ADMIN");
	}

	// Need a Password encoder.
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Specify what the logged-in user is allowed to do.
		// The order of the antMatchers() elements is significant – the more specific
		// rules need to come first, followed by the more general ones.
		// formLogin() is used to support form based authentication.
		// Only ADMIN can add new projects and employees.
		httpSecurity.authorizeRequests().antMatchers("/projects/new").hasRole("ADMIN").antMatchers("/employees/new")
				.hasRole("ADMIN").antMatchers("/").authenticated().and().formLogin();
	}
}
