package com.paras.mai.web.blogapi.exception;

import com.paras.mai.web.blogapi.payloads.ApiRsponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandller {

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value=Exception.class)
//    public ResponseEntity<ApiRsponse> resourceNotFoundExceptionHandler(Exception ex)
//    {
//        String message = ex.getMessage();
//        ApiRsponse apiRsponse = new ApiRsponse(message,false);
//        return new ResponseEntity<>(apiRsponse, HttpStatus.NOT_FOUND);
//    }
}
