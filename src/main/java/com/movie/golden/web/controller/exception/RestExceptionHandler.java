package com.movie.golden.web.controller.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse nullPointerException(NullPointerException e) {
        var exceptionResponseBuilder = new ExceptionResponseBuilder(
                "Erro interno do servidor, objeto nulo foi encontrado",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage()
        );
        return exceptionResponseBuilder.build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse noSuchElementException(NoSuchElementException e) {
        var exceptionResponseBuilder = new ExceptionResponseBuilder(
                "O recurso não foi encontrado. Verifique os parâmetros da sua solicitação",
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return exceptionResponseBuilder.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        var exceptionResponseBuilder = new ExceptionResponseBuilder(
                "Foi encontrado um erro de parâmetro",
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return exceptionResponseBuilder.build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse contraintViolationException(ConstraintViolationException e) {
        var exceptionResponseBuilder = new ExceptionResponseBuilder(
                "Foi encontrato um erro de validação, verifique os campos preenchidos",
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return exceptionResponseBuilder.build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse illegalArgumentException(IllegalArgumentException e) {
        var exceptionResponseBuilder = new ExceptionResponseBuilder(
                "Ocorreu um erro na tentativa de cadastro do filme, verifique os campos preenchidos",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage()
        );
        return exceptionResponseBuilder.build();
    }
}
