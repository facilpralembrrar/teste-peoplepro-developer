package br.com.balduino.peoplepro.user.core.application.rest.exceptions;

import br.com.balduino.peoplepro.user.core.domain.ports.inbound.InvalidStateModelException;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.ModelNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleModelNotFoundException(ModelNotFoundException e) {
        ApplicationError apiError = new ApplicationError(HttpStatus.NOT_FOUND, e.getMessage());

        return buildResponseEntity(apiError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class, InvalidStateModelException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidStateModelException(Exception e) {
        ApplicationError apiError = new ApplicationError(HttpStatus.BAD_REQUEST, e.getMessage());

        return buildResponseEntity(apiError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        String error = "Ops! Internal Server Error";

        return buildResponseEntity(new ApplicationError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApplicationError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getCode()));
    }
}
