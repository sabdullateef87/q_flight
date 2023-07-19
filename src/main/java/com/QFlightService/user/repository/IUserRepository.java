package com.QFlightService.user.repository;

import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;

public interface IUserRepository {
  User createUser(CreateUserRequest request);
  User getUserDetails();
}
