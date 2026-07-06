package com.erasm.service;

import com.erasm.dto.LoginRequestDTO;
import com.erasm.dto.LoginResponseDTO;

public interface AuthService {
	LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
	String logout();

}
