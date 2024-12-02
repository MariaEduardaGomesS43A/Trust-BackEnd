package com.ibeus.Comanda.Digital.controller;

import com.ibeus.Comanda.Digital.exception.ClientNotFoundException;
import com.ibeus.Comanda.Digital.exception.DishNotFoundException;
import com.ibeus.Comanda.Digital.exception.ErrorMessage;
import com.ibeus.Comanda.Digital.exception.OrderNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(OrderNotFoundException exception, HttpServletRequest http){
        ErrorMessage msg = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now(),
                http.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }
    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<ErrorMessage> dishNotFoundException(DishNotFoundException exception, HttpServletRequest http){
        ErrorMessage msg = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now(),
                http.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> clientNotFoundException(ClientNotFoundException exception, HttpServletRequest http){
        ErrorMessage msg = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now(),
                http.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }
}
