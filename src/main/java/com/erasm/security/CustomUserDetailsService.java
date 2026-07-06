package com.erasm.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erasm.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.erasm.entity.User user=userRepository.findByEmail(email)
				.orElseThrow(()->
				new UsernameNotFoundException("User not found with email:"+email));
		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().getRoleName())
				.build();
	}

}
