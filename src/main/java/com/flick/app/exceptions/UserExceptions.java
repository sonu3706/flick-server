package com.flick.app.exceptions;

public class UserExceptions {
  public UserExceptions() {
  }

  public static class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
      super(message);
    }
  }

  public static class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
      super(message);
    }
  }

  public static class UserIdAndPasswordMismatch extends RuntimeException {
    public UserIdAndPasswordMismatch(String message) {
      super(message);
    }
  }
}
