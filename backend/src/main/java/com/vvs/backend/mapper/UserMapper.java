package com.vvs.backend.mapper;

import com.vvs.backend.dto.UserDTO;
import com.vvs.backend.model.User;

public interface UserMapper {
  UserDTO toDTO(User user);
  User fromDTO(UserDTO usetDTO);
}
