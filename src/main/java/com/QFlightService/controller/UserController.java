package com.QFlightService.controller;

import com.QFlightService.common.model.Response;
import com.QFlightService.user.model.request.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
  @PostMapping("/create")
  public ResponseEntity<Response> createUser(@Valid @RequestBody CreateUserRequest request){
    return null;
  }
}
