package com.flick.app.exceptions;

public class MovieExceptions {
  public MovieExceptions() {
  }

  public static class MovieNotFound extends RuntimeException {
    public MovieNotFound(String message) {
      super(message);
    }
  }

  public static class MovieAlreadyExists extends RuntimeException {
    public MovieAlreadyExists(String message) {
      super(message);
    }
  }
}
