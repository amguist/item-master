package org.neoworkz.fms.item.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public @ResponseBody ResponseEntity<CustomException> handleValidationException(ValidationException validationException) {
        return new ResponseEntity<>(validationException, HttpStatus.BAD_REQUEST);
    }
}
