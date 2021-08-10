package org.neoworkz.fms.item.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties({"stackTrace","localizedMessage","suppressed"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private String              code;
    private List<ErrorDetails> errors;
}
