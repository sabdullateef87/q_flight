package com.QFlightService.common.advice;

import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.common.model.Response;
import com.QFlightService.common.utils.QUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<Response> databaseExceptionHandler(DatabaseException ex){
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    return ResponseEntity.status(500).body(Response.builder().timestamp(new Date()).error(errors).referenceId(QUtil.generateRef()).build());
  }
}
