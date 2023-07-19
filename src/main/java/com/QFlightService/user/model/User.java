package com.QFlightService.user.model;

import com.QFlightService.common.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
public class User extends BaseModel {
  @JsonProperty("first_name")
  private String firstname;
  @JsonProperty("last_name")
  private String lastname;
  @JsonProperty("email_address")
  private String email;
  @JsonProperty("address")
  private String address;
  @JsonProperty("mobile_number")
  private String mobileNumber;
  private String password;
  private String image;
  @JsonProperty("date_of_birth")
  private String dateOfBirth;
}
