package com.nagarrow.statements.exceptions;

public class AccountServiceException extends RuntimeException {

    private final AppError appError;
    public AccountServiceException(AppError appError, Throwable cause) {
        super(appError.getMessage(), cause);
        this.appError = appError;
    }
    public AccountServiceException(AppError appError) {
        super(appError.getMessage());
        this.appError = appError;
    }

    public AppError getErrorCode() {
        return appError;
    }
}
