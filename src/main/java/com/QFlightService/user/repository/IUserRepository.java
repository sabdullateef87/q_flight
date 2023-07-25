package com.QFlightService.user.repository;

import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;
import com.QFlightService.user.model.request.GetUserDetailsRequest;

public interface IUserRepository {
  User createUser(CreateUserRequest request);
  User getUserDetails(GetUserDetailsRequest request);
}
