package com.erasm.service.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.erasm.dto.LoginRequestDTO;
import com.erasm.dto.LoginResponseDTO;
import com.erasm.security.JwtService;
import com.erasm.service.AuthService;
@Service
public class AuthServiceImple implements AuthService {
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	public AuthServiceImple(AuthenticationManager authenticationManager, JwtService jwtService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequestDTO.getEmail(),
				loginRequestDTO.getPassword()));
		String token=jwtService.generateToken(loginRequestDTO.getEmail());
		return new LoginResponseDTO(token);
	}
	@Override
	public String logout() {
		// TODO Auto-generated method stub
		return "Logged out successfully";
	}
	

}
