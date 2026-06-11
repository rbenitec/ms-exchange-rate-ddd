package pe.test.exchange.domain.exception;

import lombok.Getter;

@Getter
public class ExchangeBusinessException extends RuntimeException {
    private final String code;
    public ExchangeBusinessException(String message, String code) {
        super(message);
        this.code = code;
    }
}
