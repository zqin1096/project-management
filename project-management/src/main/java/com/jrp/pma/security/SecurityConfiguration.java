package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Postgres database.
	@Autowired
	DataSource dataSource;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Define authentication mechanism.
		// Define new user name and password. The default user name is "user".
		// withDefaultSchema() creates a USERS table and AUTHORITIES table in the h2
		// database.
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
				.authoritiesByUsernameQuery("select username, role from user_accounts where username = ?")
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Specify what the logged-in user is allowed to do.
		// The order of the antMatchers() elements is significant – the more specific
		// rules need to come first, followed by the more general ones.
		// formLogin() is used to support form based authentication.
		// Only ADMIN can add new projects and employees.
		httpSecurity.authorizeRequests().antMatchers("/projects/new").hasAuthority("ADMIN")
				.antMatchers("/projects/save").hasAuthority("ADMIN").antMatchers("/employees/new").hasAuthority("ADMIN")
				.antMatchers("/employees/save").hasAuthority("ADMIN").antMatchers("/", "/**").permitAll().and()
				.formLogin();
//		httpSecurity.csrf().disable();
	}
}
/*
 * Create the user_accounts table in pgAdmin.
 * 
 * CREATE SEQUENCE IF NOT EXISTS user_accounts_seq;
 * 
 * CREATE TABLE IF NOT EXISTS user_accounts ( user_id BIGINT NOT NULL DEFAULT
 * nextval('user_accounts_seq') PRIMARY KEY, username varchar(255) NOT NULL,
 * email varchar(255) NOT NULL, password varchar(255) NOT NULL, role
 * varchar(255), enabled boolean NOT NULL );
 * 
 * Update the role in pgAdmin.
 * 
 * update user_accounts set role = 'ADMIN' where username = 'zhaoyinq'
 */
