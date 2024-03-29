package com.movie.golden.web.controller.exception;

public class ExceptionResponseBuilder {
    private String status;
    private int statusCode;
    private String error;

    public ExceptionResponseBuilder(String status, int statusCode, String error) {
        this.status = status;
        this.statusCode = statusCode;
        this.error = error;
    }

    public ExceptionResponse build() {
        var exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(this.status);
        exceptionResponse.setStatusCode(this.statusCode);
        exceptionResponse.setError(this.error);
        return exceptionResponse;
    }
}
