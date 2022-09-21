package com.laioffer.booking.Controller;

import com.laioffer.booking.exception.GCSUploadException;
import com.laioffer.booking.exception.StayNotExistException;
import com.laioffer.booking.exception.UserAlreadyExistException;
import com.laioffer.booking.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//controller handle the UserAlreadyExist exception
//@ControllerAdvice to make Spring use CustomExceptionHandler when there’s any exceptions in the Controller code.
//@ExceptionHandler to match each exception to the corresponding handler function.

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<String> handleUserAlreadyExistExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNotExistException.class)
    public final ResponseEntity<String> handleUserNotExistExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(StayNotExistException.class)
    public final ResponseEntity<String> handleStayNotExistExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GCSUploadException.class) //处理upload image报异常的response
    public final ResponseEntity<String> handleGCSUploadExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


