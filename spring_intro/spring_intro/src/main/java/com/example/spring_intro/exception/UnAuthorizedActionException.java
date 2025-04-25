package com.example.spring_intro.exception;

public class UnAuthorizedActionException extends Throwable {
  public UnAuthorizedActionException(String message) {
    super(message);
  }
}
