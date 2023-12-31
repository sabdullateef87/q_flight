package com.QFlightService.user.service;

import com.QFlightService.user.model.request.CreateUserRequest;
import com.QFlightService.user.model.request.GetUserDetailsRequest;
import com.QFlightService.user.model.response.UserResponseDto;

public interface IUserService {
  UserResponseDto createUser(CreateUserRequest request);
  UserResponseDto getUerDetail(GetUserDetailsRequest request);
}
