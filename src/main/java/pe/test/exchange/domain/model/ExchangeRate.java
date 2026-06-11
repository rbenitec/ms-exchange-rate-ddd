package pe.test.exchange.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Esta clase no sirve para definir un contrato de persistencia del dominio
 * para que al momento de guardar no dependamos de una tecnologia especifica, sino que se pueda mapear a cualquier tecnologia de persistencia
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRate {
    private Long id;
    private BigDecimal amount;
    private CurrencyCode sourceCurrency;
    private CurrencyCode targetCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;
    private LocalDateTime operationDate;
}
