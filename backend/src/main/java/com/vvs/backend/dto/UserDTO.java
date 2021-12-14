package com.vvs.backend.dto;

import com.vvs.backend.model.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.vvs.backend.model.UserRole.USER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
  
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UserRole role = USER;
  private Instant onCreate;
  private Instant onUpdate;

  public String getFullName() {
    return firstName != null ? firstName.concat(" ").concat(lastName) : "";
  }
}
