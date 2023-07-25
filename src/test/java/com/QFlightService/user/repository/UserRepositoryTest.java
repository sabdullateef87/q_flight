package com.QFlightService.user.repository;

import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.common.exception.RecordNotFoundException;
import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;
import com.QFlightService.user.model.request.GetUserDetailsRequest;
import com.QFlightService.user.service.IUserService;
import com.QFlightService.utils.TestUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTest {

  @Container
  static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));
  @DynamicPropertySource
  static void mySqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
    registry.add("spring.datasource.driverClassName", () -> mySQLContainer.getDriverClassName());
    registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
    registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
    registry.add("spring.flyway.enabled", () -> "true");
  }
  @Autowired
  private IUserRepository userRepository;

  @DisplayName("CREATE NEW USER : SUCCESSFUL")
  @Test
  void CREATE_NEW_USER_SUCCESSFUL() {
    CreateUserRequest request = TestUtil.testRequest1();
    User user = userRepository.createUser(request);

    Assertions.assertNotNull(user);
    Assertions.assertEquals(user.getEmail(), request.getEmail());
    Assertions.assertEquals(user.getLastname(), request.getLastName());
    Assertions.assertEquals(user.getMobileNumber(), request.getMobileNumber());
  }
  @DisplayName("CREATE NEW USER : THROWS ERROR")
  @Test
  void CREATE_NEW_USER_FAILED_THROWS_ERROR() {
    CreateUserRequest request = TestUtil.testRequest2();
    Assertions.assertThrows(DatabaseException.class, () -> {
      userRepository.createUser(request);
    });

  }
  @DisplayName("CREATE NEW USER : FAILED")
  @Test
  void CREATE_NEW_USER_FAILED() {
    CreateUserRequest request = TestUtil.testRequest2();
    User user = userRepository.createUser(request);

    Assertions.assertNotNull(user);
    Assertions.assertNotEquals(user.getEmail(), "email");
    Assertions.assertNotEquals(user.getLastname(), "password");
    Assertions.assertNotEquals(user.getMobileNumber(), "mobile");

  }

  @DisplayName("GET USER DETAILS FOR EXISTING USER : SUCCESSFUL")
  @Test
  void GET_USER_DETAILS_FOR_EXISTING_USER(){
    GetUserDetailsRequest request = GetUserDetailsRequest.builder()
        .email(TestUtil.testRequest1().getEmail())
        .mobileNumber(TestUtil.testRequest1().getMobileNumber())
        .build();

    User user = userRepository.getUserDetails(request);

    Assertions.assertNotNull(user);
    Assertions.assertEquals(user.getMobileNumber(), request.getMobileNumber());
    Assertions.assertEquals(user.getEmail(), request.getEmail());
  }

  @DisplayName("GET USER DETAILS : FAILED (THROWS ERROR) - DATA TOO LONG FOR EMAIL")
  @Test
  void GET_USER_DETAILS_INVALID_MOBILE_NUMBER(){
    GetUserDetailsRequest request = GetUserDetailsRequest.builder()
        .email("as@gmail.com")
        .mobileNumber("90902798087586576786897")
        .build();

    DatabaseException ex = Assertions.assertThrows(DatabaseException.class, () -> {
      userRepository.getUserDetails(request);
    });
  }

  @DisplayName("GET USER DETAILS FOR NON EXISTING USER: FAILED (THROWS ERROR)")
  @Test
  void GET_USER_DETAILS_FOR_NON_EXISTING_USER(){
    GetUserDetailsRequest request = GetUserDetailsRequest.builder()
        .email("as@gmail.com")
        .mobileNumber("9090279")
        .build();

    RecordNotFoundException ex = Assertions.assertThrows(RecordNotFoundException.class, () -> {
      userRepository.getUserDetails(request);
    });
  }
}