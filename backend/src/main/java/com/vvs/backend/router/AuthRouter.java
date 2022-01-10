package com.vvs.backend.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.vvs.backend.handler.AuthHandler;

import static org.springframework.http.MediaType.*;

@Configuration
public class AuthRouter {
  
  @Bean
  public RouterFunction<ServerResponse> authRouterFunction(AuthHandler authHandler) {
    return RouterFunctions
      .route(POST("/auth/signup").and(accept(APPLICATION_JSON)), authHandler::signup)
      .andRoute(POST("/auth/login").and(accept(APPLICATION_JSON)), authHandler::login);
  }
}
