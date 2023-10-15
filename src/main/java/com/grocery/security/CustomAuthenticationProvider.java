package com.grocery.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.grocery.entities.UserGrocery;
import com.grocery.repository.UserRepository;

	@Component
	public class CustomAuthenticationProvider implements AuthenticationProvider {

	    @Autowired
	    private UserRepository userRepository;

	    @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	        String username = authentication.getName();
	        String password = authentication.getCredentials().toString();

	        UserGrocery user = userRepository.findByUsername(username);
	        if (user != null && user.getPassword().equals(password)) {
	            List<GrantedAuthority> authorities = new ArrayList<>();
	            if (user.isAdmin()) {
	                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	            } else {
	                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	            }
	            return new UsernamePasswordAuthenticationToken(username, password, authorities);
	        } else {
	            throw new BadCredentialsException("Authentication failed");
	        }
	    }

	    @Override
	    public boolean supports(Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    }
	}


