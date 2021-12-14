package com.vvs.backend.service;

import com.vvs.backend.dto.ResponseDTO;
import com.vvs.backend.dto.UserDTO;
import com.vvs.backend.error.exception.UserAlreadyExistException;
import com.vvs.backend.error.exception.WrongCredentialException;
import com.vvs.backend.mapper.UserMapper;
import com.vvs.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JWTUtil jwtUtil;
  @Autowired
  private UserMapper userMapper;

  @Override
  public Mono<UserDTO> signup(UserDTO userDTO) {
    return isUserExist(userDTO.getEmail())
      .filter(userExist -> !userExist)
      .switchIfEmpty(Mono.error(UserAlreadyExistException::new))
      .map(aBoolean -> userDTO)
      .map(userMapper::fromDTO)
      .doOnNext(user -> user.setPassword(passwordEncoder.encode(user.getPassword())))
      .flatMap(userRepository::save)
      .map(userMapper::toDTO);
  }

  @Override
  public Mono<ResponseDTO> login(String email, String password) {
    return userRepository.findByEmail(email)
      .filter(userDetails -> passwordEncoder.matches(password, userDetails.getPassword()))
      .map(userDetails -> jwtUtil.generateToken(userDetails))
      .map(token -> ResponseDTO.builder().data(token).build())
      .switchIfEmpty(Mono.error(WrongCredentialException::new));
  }

  private Mono<Boolean> isUserExist(String email) {
    return userRepository.findByEmail(email)
      .map(user -> true)
      .switchIfEmpty(Mono.just(false));
  }
  
}
