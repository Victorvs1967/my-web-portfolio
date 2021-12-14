package com.vvs.backend.router;

import com.vvs.backend.dto.UserDTO;
import com.vvs.backend.service.JWTUtil;
import com.vvs.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserHandler {
  
  @Autowired
  private UserService userService;
  @Autowired
  private JWTUtil jwtUtil;

  public Mono<ServerResponse> getUser(ServerRequest request) {

    String token = request.headers().firstHeader("authorization").substring(7);

    Mono<?> response = jwtUtil.getAllClaimsFromToken(token)
      .flatMap(credentials -> userService.getUser(credentials.getSubject()).cast(UserDTO.class)
      .map(userDetails -> userDetails));

    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(response, UserDTO.class);
  }
}
