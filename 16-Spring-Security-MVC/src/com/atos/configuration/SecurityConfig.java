package com.atos.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(InMemoryUserDetailsManager());
	}

	private InMemoryUserDetailsManager InMemoryUserDetailsManager() {
		final Properties prop = new Properties();
		prop.put("zack", "test123,EMPLOYEE,enabled");
		return new InMemoryUserDetailsManager(prop);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().antMatchers("/resources/**").permitAll().and().formLogin()
		.loginPage("/showLoginPage").loginProcessingUrl("/confirmLogin").permitAll().and()
		.logout().permitAll();
	}

	/**
	 * 
	 * TO DO FROM HERE....
	 **/

}
