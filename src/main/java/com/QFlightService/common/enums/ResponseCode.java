package com.QFlightService.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
  ACCEPTED("02", "Accepted"),
  BAD_REQUEST("31", "Invalid request, check you request and try again"),
  SUCCESSFUL("00", "Successful");

  private final String code;
  private final String message;
}
