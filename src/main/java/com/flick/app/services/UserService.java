package com.flick.app.services;

import com.flick.app.models.Password;
import com.flick.app.models.User;

import java.util.Map;

import static com.flick.app.exceptions.UserExceptions.*;

public interface UserService {

  Map<String, String> loginUser(String userEmail, String password) throws UserIdAndPasswordMismatch, UserNotFound;

  Map<String, Boolean> registerUser(User user) throws UserAlreadyExists;

  Boolean changePassword(Password password) throws UserNotFound;

  Boolean changeProfile(User user);

  User getUserProfileByUserEmail(String userEmail) throws UserNotFound;
}
