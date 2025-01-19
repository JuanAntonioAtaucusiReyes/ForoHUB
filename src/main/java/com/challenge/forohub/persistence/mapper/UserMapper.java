package com.challenge.foroHub.persistence.mapper;

import java.util.List;

import com.challenge.foroHub.persistence.dto.auth.request.RegisterRequest;
import com.challenge.foroHub.persistence.dto.user.request.UpdateUserRequest;
import com.challenge.foroHub.persistence.dto.user.response.UserResponse;
import com.challenge.foroHub.persistence.entity.Role;
import com.challenge.foroHub.persistence.entity.User;

public class UserMapper {

  public static UserResponse toUserDto(User user) {
    if (user == null) {
      return null;
    }

    return new UserResponse(
        user.getId(),
        user.getUsername()
    );
  }

  public static List<UserResponse> toUserDtoList(List<User> users) {
    if (users == null) {
      return null;
    }

    return users.stream()
        .map(UserMapper::toUserDto)
        .toList();
  }

  public static User toUserEntity(RegisterRequest userDto) {
    if (userDto == null) {
      return null;
    }

    User user = new User();
    user.setName(userDto.name());
    user.setUsername(userDto.username());
    user.setPassword(userDto.password());
    user.setRole(Role.USER);

    return user;
  }

  public static void updateUserEntity(User oldUser, UpdateUserRequest userDto) {
    if (oldUser != null && userDto != null) {
      if (userDto.name() != null) {
        oldUser.setName(userDto.name());
      }
      if (userDto.role() != null) {
        oldUser.setRole(userDto.role());
      }
      if (userDto.password() != null) {
        oldUser.setPassword(userDto.password());
      }
    }
  }
}
