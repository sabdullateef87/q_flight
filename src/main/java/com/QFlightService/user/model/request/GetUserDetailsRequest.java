package com.QFlightService.user.model.request;

import com.QFlightService.common.exception.BadRequestException;
import com.QFlightService.common.utils.QUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class GetUserDetailsRequest {
  private String email;
  @JsonProperty("mobile_number")
  private String mobileNumber;

  public GetUserDetailsRequest softValidate(){
    if(QUtil.isEmpty(this.email) && QUtil.isEmpty(this.mobileNumber)){
      throw new BadRequestException("Invalid request, both fields cannot be empty or null");
    }
    return this;
  }
}
