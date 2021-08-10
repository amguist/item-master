package org.neoworkz.fms.item.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonPropertyOrder({"message","field","path"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails implements Serializable {
    private String message;
    private String field;
    private String path;

    public ErrorDetails(String message, String field, String path) {
        this.message = message;
        this.field = field;
        this.path = path;
    }

    public ErrorDetails(String message, String path) {
        this.message = message;
        this.path = path;
    }
}
