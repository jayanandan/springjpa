package com.example.springjpa.filter;

import com.example.springjpa.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleException(Exception e, WebRequest request){
        return new ResponseEntity(new ApiError("Problem in handling the request"),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
