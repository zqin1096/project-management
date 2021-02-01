package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

	// h2 database.
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Define authentication mechanism.
		// Define new user name and password. The default user name is "user".
		// withDefaultSchema() creates a USERS table and AUTHORITIES table in the h2
		// database.
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("myuser").password("pass")
				.roles("USER").and().withUser("zhaoyin").password("pass2").roles("USER").and().withUser("managerUser")
				.password("pass3").roles("ADMIN");
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
				.hasRole("ADMIN").antMatchers("h2-console/**").permitAll().antMatchers("/").authenticated().and()
				.formLogin();

		// Access the /h2-console.
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}
}
