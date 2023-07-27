package com.QFlightService.controller;

import com.QFlightService.common.enums.ResponseCode;
import com.QFlightService.common.model.Response;
import com.QFlightService.common.utils.QUtil;
import com.QFlightService.user.model.request.CreateUserRequest;
import com.QFlightService.user.model.request.GetUserDetailsRequest;
import com.QFlightService.user.model.response.UserResponseDto;
import com.QFlightService.user.service.IUserService;
import com.QFlightService.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private IUserService userService;
  @PostMapping("/create")
  public ResponseEntity<Response> createUser(@Valid @RequestBody CreateUserRequest request){
    UserResponseDto response = userService.createUser(request);
    Response res = Response.builder()
        .referenceId(QUtil.generateRef())
        .data(response)
        .timestamp(new Date())
        .responseCode(ResponseCode.SUCCESSFUL.getCode())
        .responseMessage("User Created Successfully").build();
    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }


  @GetMapping()
  public ResponseEntity<Response> getUserDetails(@Valid GetUserDetailsRequest request){
    UserResponseDto response = userService.getUerDetail(request);
    Response res = Response.builder()
        .referenceId(QUtil.generateRef())
        .data(response)
        .timestamp(new Date())
        .responseCode(ResponseCode.SUCCESSFUL.getCode())
        .responseMessage("Successful").build();
    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}
