package com.QFlightService.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException{
  private String code;

  public BaseException(String message){
    super(message);
  }
  public BaseException(String message, String code){
    super(message);
    this.code = code;
  }

}
