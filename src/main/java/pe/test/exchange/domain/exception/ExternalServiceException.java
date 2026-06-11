package pe.test.exchange.domain.exception;

import lombok.Getter;

@Getter
public class ExternalServiceException extends RuntimeException {

    private final String code;
    private final String status;

    public ExternalServiceException(String message, String code, String status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
