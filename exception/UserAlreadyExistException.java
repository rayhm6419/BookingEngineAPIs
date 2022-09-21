package com.laioffer.booking.exception;

//如果用户曾经被注册过了，那就throw exception（异常）
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}