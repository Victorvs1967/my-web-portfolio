package com.vvs.backend.model;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Document("users")
public class User implements UserDetails {
  
  @Id
  private String id;
  private String firstName;
  private String lastName;
  @NonNull
  private String email;
  @NonNull
  private String password;
  private Instant onCreate;
  private Instant onUpdate;
  private boolean isActive = true;

  UserRole role = UserRole.USER;

  User() {
    this.onCreate = Instant.now();
    this.onUpdate = this.onCreate;
  }

  public void setOnUpdate() {
    this.onUpdate = Instant.now();
  }

  public String getFullName() {
    return firstName != null ? firstName.concat(" ").concat(lastName) : "";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isActive;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

}
