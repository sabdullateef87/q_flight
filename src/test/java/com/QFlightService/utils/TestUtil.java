package com.QFlightService.utils;

import com.QFlightService.user.model.request.CreateUserRequest;

public class TestUtil {

  public static CreateUserRequest testRequest1(){
    return CreateUserRequest.builder()
        .firstName("Abdullateef")
        .lastName("suleiman")
        .email("sabdullateef@gg.com")
        .password("just plain password")
        .image("")
        .dateOfBirth("")
        .mobileNumber("909291901")
        .build();
  }
  public static CreateUserRequest testRequest2(){
    return CreateUserRequest.builder()
        .firstName("Abdullateef")
        .lastName("suleiman")
        .email("sabdullateef@g1.com")
        .password("just plain password")
        .image("")
        .dateOfBirth("")
        .mobileNumber("9092591901")
        .build();
  }
}
