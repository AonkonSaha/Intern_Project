package com.example.spring_intro.exception;

public class RoleNotFoundException extends RuntimeException {
  public RoleNotFoundException(String message) {
    super(message);
  }
}
