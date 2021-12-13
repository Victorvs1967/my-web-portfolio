package com.vvs.backend.repository;

import com.vvs.backend.model.User;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
  Mono<UserDetails> findByEmail(String email);
  Mono<User> findUserByEmail(String email);
}
