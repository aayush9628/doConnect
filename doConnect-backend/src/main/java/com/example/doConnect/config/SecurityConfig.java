package com.example.doConnect.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.doConnect.filter.JwtFilter;
import com.example.doConnect.service.LogoutService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	private final LogoutService logoutService;
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/auth/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.logout(logout -> logout.logoutUrl("/auth/logout")
				.addLogoutHandler(logoutService)
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
