package com.capgemini.test.code.hexagonal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.test.code.hexagonal.exceptions.BadRequestException;
import com.capgemini.test.code.hexagonal.exceptions.DuplicateKeyException;
import com.capgemini.test.code.hexagonal.exceptions.InvalidDataException;
import com.capgemini.test.code.hexagonal.exceptions.NotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {
    public class ErrorResponse {
        private int code;
        private String message;

        public ErrorResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

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

        // Getters y setters
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException ex) {
        String joinedFields = String.join(" | ", ex.getErrors().keySet());
        if (joinedFields.isEmpty()) {
            joinedFields = ex.getMessage();
        }
        String message = "error validation <" + joinedFields + ">";
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, message));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKey(DuplicateKeyException ex) {
        String message = "Duplicate entry or constraint violation (email, dni, etc)";
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, message));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Data integrity violation";

        // Extraer el campo que falló, si es posible
        String detail = ex.getMostSpecificCause().getMessage();
        String field = extractConstraintField(detail);

        if (field != null) {
            message = "error validation: <" + field + ">";
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, message));
    }

    private String extractConstraintField(String detail) {
        // Esto depende del motor de base de datos (este es un ejemplo para PostgreSQL)
        if (detail != null && detail.contains("Key (")) {
            int start = detail.indexOf("Key (") + 5;
            int end = detail.indexOf(")", start);
            return detail.substring(start, end);
        }
        return null;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, message));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, message));
    }

}
