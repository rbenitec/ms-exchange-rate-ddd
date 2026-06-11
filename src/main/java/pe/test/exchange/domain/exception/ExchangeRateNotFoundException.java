package pe.test.exchange.domain.exception;

import lombok.Getter;

@Getter
public class ExchangeRateNotFoundException extends RuntimeException  {
    private final String code;
    public ExchangeRateNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
