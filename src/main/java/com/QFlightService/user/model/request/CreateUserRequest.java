package com.QFlightService.user.model.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String dateOfBirth;
  private String address;
  private String mobileNumber;
  private String image;
}
