package org.neoworkz.fms.item.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends CustomException {

    public ValidationException(String code, List<ErrorDetails> errors) {
        super(code, errors);
    }
}
