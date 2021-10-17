package com.yur.brod.geolocationconversionsystem.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;

@RestController
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex
            , Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestApiResponse response =  new RestApiResponse(
                LocalTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonMappingException.class)
    public final ResponseEntity<Object> handleJsonMappingException(Exception ex, WebRequest request){
        RestApiResponse response =  new RestApiResponse(
                LocalTime.now(),
                "Wrong data format",
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleValidationException(Exception ex, WebRequest request){
        RestApiResponse response =  new RestApiResponse(
                LocalTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public final ResponseEntity<Object> handleAddressNotFoundException(Exception ex, WebRequest request){
        RestApiResponse response =  new RestApiResponse(
                LocalTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(JsonProcessingException.class)
    public final ResponseEntity<Object> handleJsonProcessingException(Exception ex, WebRequest request){
        RestApiResponse response =  new RestApiResponse(
                LocalTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
