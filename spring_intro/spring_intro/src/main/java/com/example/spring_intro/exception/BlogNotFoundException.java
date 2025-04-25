package com.example.spring_intro.exception;

public class BlogNotFoundException extends Throwable {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
