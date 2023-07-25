package com.QFlightService.common.exception;

import lombok.Getter;
import lombok.Setter;

public class BadRequestException extends RuntimeException{
  private String message;
  private String code;

  public BadRequestException(String message){
    super(message);
  }

  public BadRequestException(String message, String code){
    super(message);
    this.code = code;
  }
}
