package hyk.springframework.springtravellogrestapi.controller.api;

import hyk.springframework.springtravellogrestapi.exception.ErrorResponse;
import hyk.springframework.springtravellogrestapi.exception.TravelLogNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Htoo Yanant Khin
 */
@Slf4j
@ControllerAdvice
public class TravelLogExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle all exceptions
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
        log.error("General Exception: ", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorMessage(exception.getLocalizedMessage());
        errorResponse.setRequestedUrl(((ServletWebRequest)request).getRequest().getRequestURL().toString());
        return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // Handle only one specific exception
    @ExceptionHandler(TravelLogNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        log.error("Travel Log Not Found Exception: ", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorMessage(exception.getLocalizedMessage());
        errorResponse.setRequestedUrl(((ServletWebRequest)request).getRequest().getRequestURL().toString());
        return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}