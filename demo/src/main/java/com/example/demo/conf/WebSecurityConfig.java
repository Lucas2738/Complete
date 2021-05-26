package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeExchange()
				.pathMatchers("/actuator/**").permitAll()
				.pathMatchers(HttpMethod.GET, "/rest/*")
				.hasRole("USER")
				.pathMatchers(HttpMethod.POST, "/rest/*")
				.permitAll()
				.pathMatchers("/inheritance/*")
				.permitAll()
				.pathMatchers("/rx/*")
				.permitAll()
				.pathMatchers("/chat")
				.permitAll()
				.pathMatchers("/home")
				.permitAll()
				.and()
				.httpBasic();
		return http.build();
	}

	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		UserDetails user = User
				.withUsername("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		return new MapReactiveUserDetailsService(user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
