package com.vvs.backend.router;

import com.vvs.backend.dto.ResponseDTO;
import com.vvs.backend.dto.UserDTO;
import com.vvs.backend.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class AuthHandler {
  
  @Autowired
  private AuthService authService;

  public Mono<ServerResponse> signup(ServerRequest request) {
    Mono<UserDTO> user = request.bodyToMono(UserDTO.class)
      .flatMap(credentials -> authService.signup(credentials).cast(UserDTO.class)
      .map(userDetails -> userDetails));

    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(user, UserDTO.class);
  }

public Mono<ServerResponse> login(ServerRequest request) {

  Mono<?> response = request.bodyToMono(UserDTO.class)
    .flatMap(credentials -> authService.login(credentials.getEmail(), credentials.getPassword()).cast(ResponseDTO.class)
    .map(userDetails -> userDetails.getData()));
    
  return ServerResponse
    .ok()
    .contentType(APPLICATION_JSON)
    .body(response, ResponseDTO.class);
}
}
