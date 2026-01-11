package com.example.userService.exception;

import com.example.userService.payload.ApiResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<ApiResponse> handleServiceUnavailable(FeignException ex) {

        String serviceName = extractServiceName(ex);

        ApiResponse response = new ApiResponse();
        response.setMessage(serviceName + " is currently unavailable. Please try again later.");
        response.setSuccess(false);
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private String extractServiceName(FeignException ex) {
        try {
            String url = ex.request().url();   // http://RATING-SERVICE/...
            String withoutProtocol = url.replace("http://", "").replace("https://", "");
            return withoutProtocol.split("/")[0];
        } catch (Exception e) {
            return "Dependent service";
        }
    }

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
