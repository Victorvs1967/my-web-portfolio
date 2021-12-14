package com.vvs.backend.mapper;

import java.time.Instant;

import com.vvs.backend.dto.UserDTO;
import com.vvs.backend.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public UserDTO toDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail(user.getEmail());
    userDTO.setPassword(user.getPassword());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setOnCreate(user.getOnCreate());
    return userDTO;
  }

  @Override
  public User fromDTO(UserDTO userDTO) {
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setRole(userDTO.getRole());
    // user.setOnCreate(userDTO.getOnCreate());
    user.setOnUpdate(Instant.now());

    return user;
  }
  
}
