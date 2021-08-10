package org.neoworkz.fms.item.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    INVALID_REQUEST_ERROR("error.request.invalid");

    private String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }
}
