package com.vvs.backend.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class UserRouter {
  
  @Bean
  public RouterFunction<ServerResponse> userRouterFunction(UserHandler userHandler) {
    return RouterFunctions
      .route(GET("/user").and(accept(APPLICATION_JSON)), userHandler::getUser);
  }
}
