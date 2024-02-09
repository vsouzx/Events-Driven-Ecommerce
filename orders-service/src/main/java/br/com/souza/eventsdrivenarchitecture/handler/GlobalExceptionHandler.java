package br.com.souza.eventsdrivenarchitecture.handler;

import br.com.souza.eventsdrivenarchitecture.dto.CustomErrorResponse;
import br.com.souza.eventsdrivenarchitecture.exceptions.InsufficientStockException;
import br.com.souza.eventsdrivenarchitecture.exceptions.OrderNotFoundException;
import br.com.souza.eventsdrivenarchitecture.exceptions.ProductNameAlreadyExistsException;
import br.com.souza.eventsdrivenarchitecture.exceptions.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Object> handleInsufficientException(InsufficientStockException e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError("Insuficient products on stock");
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError("Order not found");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError("Product not found");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProductNameAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductNameAlreadyExistsException(ProductNameAlreadyExistsException e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError("Already exists a product with this name");
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
