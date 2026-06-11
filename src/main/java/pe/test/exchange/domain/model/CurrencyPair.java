package pe.test.exchange.domain.model;

import pe.test.exchange.domain.exception.ExchangeBusinessException;

/**
 * Contiene modelos y value objects del dominio, se aprovecha para validar datos tecnicos del negocio.
 */
public record CurrencyPair(CurrencyCode source, CurrencyCode target) {
    public CurrencyPair {
        if (source.equals(target)) {
            throw new ExchangeBusinessException(
                    "La moneda origen y destino no pueden ser iguales",
                    "BS-ERR-001"
            );
        }
    }
}
