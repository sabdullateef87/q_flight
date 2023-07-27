package com.QFlightService.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
  ACCEPTED("02", "Accepted"),
  BAD_REQUEST("31", "Invalid request, check you request and try again"),
  NOT_RECORD_FOUND("19", "Requested resource could not be found"),
  INTERNAL_SERVER_ERROR("93", "Internal server error"),
  SUCCESSFUL("00", "Successful");

  private final String code;
  private final String message;
}
