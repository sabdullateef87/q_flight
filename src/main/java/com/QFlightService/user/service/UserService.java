package com.QFlightService.user.service;

import com.QFlightService.common.exception.BaseException;
import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;
import com.QFlightService.user.model.response.UserResponseDto;
import com.QFlightService.user.repository.IUserRepository;
import com.QFlightService.user.util.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserResponseDto createUser(CreateUserRequest request) {
    try {
      User newUser = userRepository.createUser(request);
      return UserMapper.mapUserToUserResponseDto(newUser);
    }catch (Exception ex){
      throw new BaseException(ex.getMessage());
    }
  }
}
