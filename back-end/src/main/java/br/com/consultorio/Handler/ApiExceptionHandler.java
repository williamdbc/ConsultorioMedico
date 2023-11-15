package br.com.consultorio.Handler;

import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundExcepction;
import br.com.consultorio.Exception.StandardError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<StandardError> businessRuleException(final BusinessException ex, final HttpServletRequest req) {

        StandardError error = new StandardError();

        error.setError("Bad request");
        error.setTimestamp(Instant.now());
        error.setStatus(BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setPath(req.getRequestURI());

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundExcepction.class)
    protected ResponseEntity<StandardError> entityNotFoundException(final EntityNotFoundExcepction ex, final HttpServletRequest req) {

        StandardError error = new StandardError();

        error.setError("Entity not found");
        error.setTimestamp(Instant.now());
        error.setStatus(NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setPath(req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}