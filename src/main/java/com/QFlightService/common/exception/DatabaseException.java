package com.QFlightService.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseException extends RuntimeException{
  private String code;
  public DatabaseException(String message){
    super(message);
  }
  public DatabaseException(String message, String code){
    super(message);
    this.code = code;
  }
}
