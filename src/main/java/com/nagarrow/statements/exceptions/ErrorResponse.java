package com.nagarrow.statements.exceptions;

public class ErrorResponse {
    private int code;
    private String message;
    private Object additionalDate;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getAdditionalDate() {
        return additionalDate;
    }

    public void setAdditionalDate(Object additionalDate) {
        this.additionalDate = additionalDate;
    }

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
