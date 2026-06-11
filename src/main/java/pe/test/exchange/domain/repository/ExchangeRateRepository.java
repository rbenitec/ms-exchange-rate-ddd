package pe.test.exchange.domain.repository;

import pe.test.exchange.domain.model.ExchangeRate;

/**
 * Define el contrato de persistencia con el dominio
 */
public interface ExchangeRateRepository {
    ExchangeRate save(ExchangeRate exchangeRate);
}
