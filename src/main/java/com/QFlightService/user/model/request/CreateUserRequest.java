package com.QFlightService.user.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  @Email
  private String email;
  @NotNull
  private String password;
  private String dateOfBirth;
  private String address;
  @NotNull
  private String mobileNumber;
  private String image;
}
