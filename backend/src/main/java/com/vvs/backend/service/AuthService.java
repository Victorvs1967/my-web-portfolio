package com.vvs.backend.service;

import com.vvs.backend.dto.ResponseDTO;
import com.vvs.backend.dto.UserDTO;

import reactor.core.publisher.Mono;

public interface AuthService {
  Mono<UserDTO> signup(UserDTO userDTOemail);
  Mono<ResponseDTO> login(String email, String password);
}
