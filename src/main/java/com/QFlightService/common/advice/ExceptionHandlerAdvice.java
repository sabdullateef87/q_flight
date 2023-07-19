package com.QFlightService.common.advice;

import com.QFlightService.common.enums.ResponseCode;
import com.QFlightService.common.exception.BaseException;
import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.common.model.Response;
import com.QFlightService.common.utils.QUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  public ResponseEntity<Response> validationException(Exception ex){
    List<String> errors = new ArrayList<>();
    if(ex instanceof  MethodArgumentNotValidException){
      BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
      result.getFieldErrors().forEach(error -> errors.add(error.getField() + " : " + error.getDefaultMessage()));
    }
    return ResponseEntity.badRequest().body(Response.builder()
        .timestamp(new Date())
        .error(errors)
        .referenceId(QUtil.generateRef())
        .responseCode(ResponseCode.BAD_REQUEST.getCode())
        .responseMessage(ResponseCode.BAD_REQUEST.getMessage()).build());
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<Response> databaseExceptionHandler(DatabaseException ex){
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    return ResponseEntity.status(500).body(Response.builder()
        .timestamp(new Date())
        .error(errors)
        .referenceId(QUtil.generateRef())
        .build());
  }

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<Response> baseExceptionHandler(BaseException ex){
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    return ResponseEntity.status(500).body(Response.builder().timestamp(new Date()).error(errors).referenceId(QUtil.generateRef()).build());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Response> baseExceptionHandler(HttpMessageNotReadableException ex){
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    return ResponseEntity.badRequest().body(Response.builder().timestamp(new Date()).error(errors).referenceId(QUtil.generateRef()).build());
  }


}
