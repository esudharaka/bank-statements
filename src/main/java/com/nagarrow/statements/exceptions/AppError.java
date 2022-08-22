package com.nagarrow.statements.exceptions;

import org.springframework.http.HttpStatus;


public enum AppError {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1000,
            "Internal Server Error"),
    ACCOUNT_MOT_FOUND(HttpStatus.NOT_FOUND, 1001,
            "Account not found"),

    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, 1002,
            "Incorrect Date formart. Please follow dd.MM.yyyy"),

    INVALID_USER_ROLE(HttpStatus.UNAUTHORIZED, 1003,
            "Unknown user role"),

    INVALID_DATE_FILTER(HttpStatus.BAD_REQUEST, 1004,
            "Either fromDate or toDate is blank"),

    INVALID_AMOUNT_FILTER(HttpStatus.BAD_REQUEST, 1005,
            "Either fromDate or toDate is blank"),
    UN_AUTHORIZED_OPERATION(HttpStatus.UNAUTHORIZED, 1006,
            "UnAuthorized operation");
    HttpStatus httpCode;
    int applicationErrorCode;
    String message;

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public int getApplicationErrorCode() {
        return applicationErrorCode;
    }

    public void setApplicationErrorCode(int applicationErrorCode) {
        this.applicationErrorCode = applicationErrorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    AppError(HttpStatus httpCode, int applicationErrorCode, String message) {
        this.httpCode = httpCode;
        this.applicationErrorCode = applicationErrorCode;
        this.message = message;
    }
}
