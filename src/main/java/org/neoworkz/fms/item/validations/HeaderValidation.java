package org.neoworkz.fms.item.validations;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.neoworkz.fms.item.constants.HeaderConstants.HEADER_CORRELATION_IDENTIFIER;

@Slf4j
public class HeaderValidation {

    private HeaderValidation() {

    }

    public static void validateRequestHeaders(HttpServletRequest request) {
        validateCorrelationIdentifier(request);
    }

    private static void validateCorrelationIdentifier(HttpServletRequest request) {
        String correlationIdentifier = request.getHeader(HEADER_CORRELATION_IDENTIFIER);
        if(correlationIdentifier == null || correlationIdentifier.isEmpty()) {
            correlationIdentifier = UUID.randomUUID().toString();
        }
        MDC.put(HEADER_CORRELATION_IDENTIFIER, correlationIdentifier);
    }
}
