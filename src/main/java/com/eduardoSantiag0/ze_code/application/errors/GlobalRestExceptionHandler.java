package com.eduardoSantiag0.ze_code.application.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(PartnerNotFoundException.class)
    public ResponseEntity<String> handlePartnerNotFoundException
            (PartnerNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException
            (InvalidCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    @ExceptionHandler(InvalidGeoJsonException.class)
    public ResponseEntity<String> handleInvalidGeoJsonException
            (InvalidGeoJsonException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoPartnerCloseEnoughException.class)
    public ResponseEntity<String> handleNoPartnerCloseEnoughException
            (NoPartnerCloseEnoughException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<String> handleDuplicateIdException
            (DuplicateIdException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicatedDocumentException.class)
    public ResponseEntity<String> handleDuplicatedDocumentException
            (DuplicatedDocumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


}
