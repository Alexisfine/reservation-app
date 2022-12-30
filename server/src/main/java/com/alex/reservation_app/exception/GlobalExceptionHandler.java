package com.alex.reservation_app.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorObject> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorObject> handleHotelNotFoundException(HotelNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorObject> handleHotelNotFoundException(UserNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ErrorObject> handleHotelNotFoundException(IllegalOperationException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.FORBIDDEN);
    }


}
