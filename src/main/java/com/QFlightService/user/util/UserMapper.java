package com.QFlightService.user.util;

import com.QFlightService.user.model.User;
import com.QFlightService.user.model.response.UserResponseDto;

public class UserMapper {

  public static UserResponseDto mapUserToUserResponseDto(User user){
    return UserResponseDto.builder()
        .firstName(user.getFirstname())
        .lastName(user.getLastname())
        .email(user.getEmail())
        .phoneNumber(user.getMobileNumber())
        .build();
  }
}
