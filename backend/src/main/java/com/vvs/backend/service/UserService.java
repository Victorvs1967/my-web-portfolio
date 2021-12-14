package com.vvs.backend.service;

import com.vvs.backend.dto.UserDTO;

import reactor.core.publisher.Mono;

public interface UserService {
  Mono<UserDTO> getUser(String email);
}
