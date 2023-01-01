package com.alex.reservation_app.exception;

import com.alex.reservation_app.utils.R;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<R> handleHotelNotFoundException(HotelNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        ex.printStackTrace();
        R response = new R(errorObject);

        return new ResponseEntity<R>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<R> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        ex.printStackTrace();

        R response = new R(errorObject);

        return new ResponseEntity<R>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<R> handleIllegalOperationException(IllegalOperationException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        ex.printStackTrace();
        R response = new R(errorObject);

        return new ResponseEntity<R>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<R> handleRoomNotFoundException(RoomNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        ex.printStackTrace();
        R response = new R(errorObject);

        return new ResponseEntity<R>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<R> handleException(Exception ex) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                ZonedDateTime.now()
        );
        ex.printStackTrace();
        R response = new R(errorObject);
        return new ResponseEntity<R>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
