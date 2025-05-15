package com.horrorcore.bankapp.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @ControllerAdvice and @RestControllerAdvice annotations are used to handle exceptions globally in a Spring Boot application.
// @RestControllerAdvice is a specialization of @ControllerAdvice that combines @ControllerAdvice and @ResponseBody.
// This class handles exceptions thrown by the application and returns appropriate HTTP responses.

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type UserNotFoundException and AccountNotFoundException.
     *
     * @param ex The exception that was thrown.
     * @param request The HttpServletRequest object containing details of the HTTP request.
     * @return A ResponseEntity containing an ApiError object with details about the error and a 404 NOT FOUND status.
     */
    @ExceptionHandler({UserNotFoundException.class, AccountNotFoundException.class})
    public ResponseEntity<ApiError> exceptionHandler(RuntimeException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                ex.getMessage(), // The error message from the exception.
                request.getRequestURI(), // The URI of the request that caused the exception.
                HttpStatus.NOT_FOUND.value() // HTTP status code 404.
        );
        return ResponseEntity.status(404).body(apiError); // Return the error response with status 404.
    }

    /**
     * Handles all other exceptions that are not specifically handled by other methods.
     *
     * @param ex The exception that was thrown.
     * @param request The HttpServletRequest object containing details of the HTTP request.
     * @return A ResponseEntity containing an ApiError object with details about the error and a 500 INTERNAL SERVER ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                ex.getMessage(), // The error message from the exception.
                request.getRequestURI(), // The URI of the request that caused the exception.
                HttpStatus.INTERNAL_SERVER_ERROR.value() // HTTP status code 500.
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError); // Return the error response with status 500.
    }
}
