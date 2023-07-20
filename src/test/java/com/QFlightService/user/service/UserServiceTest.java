package com.QFlightService.user.service;

import com.QFlightService.user.repository.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

  @Mock
  private IUserRepository userRepository;

  @InjectMocks
  private IUserService userService;

  @DisplayName("CREATE USER SERVICE SUCCESSFUL")
  @Test
  void CREATE_USER_SERVICE_SUCCESSFUL() {

  }
}