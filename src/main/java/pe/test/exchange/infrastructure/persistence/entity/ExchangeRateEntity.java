package pe.test.exchange.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Aca defino mi clase entidad con la cual voy a persistie
 */
@Entity
@Table(name = "exchange_conversions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;
    private LocalDateTime operationDate;
}
