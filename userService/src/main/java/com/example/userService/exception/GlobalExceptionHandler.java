package com.example.userService.exception;

import com.example.userService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setStatus(HttpStatus.NOT_FOUND);
        apiResponse.setSuccess(false);
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ApiResponse> handlerResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setMessage(ex.getMessage());
    apiResponse.setStatus(HttpStatus.CONFLICT);
    apiResponse.setSuccess(false);
    return new  ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
  }
}
