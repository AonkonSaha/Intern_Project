package com.example.spring_intro.exception;

public class CommentNotFoundException extends Throwable {
  public CommentNotFoundException(String message) {
    super(message);
  }
}
