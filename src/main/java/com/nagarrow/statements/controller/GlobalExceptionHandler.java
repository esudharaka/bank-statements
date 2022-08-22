package com.nagarrow.statements.controller;

import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;
import com.nagarrow.statements.exceptions.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccountServiceException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException
            (AccountServiceException ex, WebRequest request) {
        AppError appError = ex.getErrorCode();
        ErrorResponse error = new ErrorResponse(appError.getApplicationErrorCode(),
                appError.getMessage());
        LOGGER.error(ex);
        return new ResponseEntity<>(error, appError.getHttpCode());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException
            (MethodArgumentNotValidException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Validation error");
        LOGGER.error(ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
