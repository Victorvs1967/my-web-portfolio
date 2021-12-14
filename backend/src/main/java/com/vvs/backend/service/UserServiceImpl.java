package com.vvs.backend.service;

import com.vvs.backend.dto.UserDTO;
import com.vvs.backend.error.exception.UserNotFoundException;
import com.vvs.backend.mapper.UserMapper;
import com.vvs.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserMapper userMapper;

  @Override
  public Mono<UserDTO> getUser(String email) {
    return userRepository.findUserByEmail(email)
      .map(userMapper::toDTO)
      .switchIfEmpty(Mono.error(UserNotFoundException::new));
  }
}
