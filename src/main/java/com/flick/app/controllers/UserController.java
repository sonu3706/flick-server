package com.flick.app.controllers;

import com.flick.app.models.Password;
import com.flick.app.models.User;
import com.flick.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(201).body(this.userService.registerUser(user));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> loginUser(@RequestBody User user) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(this.userService.loginUser(user.getUserEmail(), user.getPassword()));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  } 

  @PostMapping(value = "/change-password")
  public ResponseEntity<?> changePassword(@RequestBody Password password) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(this.userService.changePassword(password));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }

  @PostMapping(value = "/change-profile")
  public ResponseEntity<?> changeProfile(@RequestBody User user) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(this.userService.changeProfile(user));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getUserProfileByUserEmail(@PathVariable("id") String id) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(this.userService.getUserProfileByUserEmail(id));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }
}
