package com.QFlightService.common.exception;

import lombok.Getter;
import lombok.Setter;

public class RecordNotFoundException extends RuntimeException{
  private String message;
  private String code;

  public RecordNotFoundException(String message){
    super(message);
  }

  public RecordNotFoundException(String message, String code){
    super(message);
    this.code = code;
  }
}
