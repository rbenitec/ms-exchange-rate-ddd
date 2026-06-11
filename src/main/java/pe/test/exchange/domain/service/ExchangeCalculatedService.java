package pe.test.exchange.domain.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Contiene reglas de negocio puras
 * Aqui se calcula monto convertido usando el tipo de cambio
 */
@Component
public class ExchangeCalculatedService {
    public BigDecimal calculate(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
