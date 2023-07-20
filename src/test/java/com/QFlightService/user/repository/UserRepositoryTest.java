package com.QFlightService.user.repository;

import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;
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

  @DisplayName("CREATE NEW USER SUCCESSFUL")
  @Test
  void CREATE_NEW_USER_SUCCESSFUL() {
    CreateUserRequest request = TestUtil.testRequest1();
    User user = userRepository.createUser(request);

    Assertions.assertNotNull(user);
    Assertions.assertEquals(user.getEmail(), request.getEmail());
    Assertions.assertEquals(user.getLastname(), request.getLastName());
    Assertions.assertEquals(user.getMobileNumber(), request.getMobileNumber());
  }
  @DisplayName("CREATE NEW USER THROWS ERROR")
  @Test
  void CREATE_NEW_USER_FAILED_THROWS_ERROR() {
    CreateUserRequest request = TestUtil.testRequest2();
    Assertions.assertThrows(DatabaseException.class, () -> {
      userRepository.createUser(request);
    });

  }
  @DisplayName("CREATE NEW USER FAILED")
  @Test
  void CREATE_NEW_USER_FAILED() {
    CreateUserRequest request = TestUtil.testRequest2();
    User user = userRepository.createUser(request);

    Assertions.assertNotNull(user);
    Assertions.assertNotEquals(user.getEmail(), "email");
    Assertions.assertNotEquals(user.getLastname(), "password");
    Assertions.assertNotEquals(user.getMobileNumber(), "mobile");

  }
}