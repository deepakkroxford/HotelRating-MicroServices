package com.lcwd.rating.RatingService.exception;

import com.lcwd.rating.RatingService.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setStatus(HttpStatus.NOT_FOUND);
        apiResponse.setSuccess(false);
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
