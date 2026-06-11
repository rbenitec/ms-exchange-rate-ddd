package pe.test.exchange.domain.model;

import pe.test.exchange.domain.exception.ExchangeBusinessException;

import java.util.Set;

/**
 * Contiene modelos y value objects del dominio, se aprovecha para validar datos tecnicos del negocio.
 * @param value
 */

public record CurrencyCode (

    String value
){
    private static final Set<String> SUPPORTED_CURRENCIES =
            Set.of("USD", "PEN", "EUR", "BRL", "CLP");

    private static final Set<String> BLOCKED_CURRENCIES =
            Set.of("RUB", "VES");

    public CurrencyCode {
        if (!SUPPORTED_CURRENCIES.contains(value)) {
            throw new ExchangeBusinessException("La moneda no está soportada por el negocio", "BS-ERR-002");
        }

        if (BLOCKED_CURRENCIES.contains(value)) {
            throw new ExchangeBusinessException("La moneda está bloqueada para operaciones", "BS-ERR-003");
        }
    }
}
