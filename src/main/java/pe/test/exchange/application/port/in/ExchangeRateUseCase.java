package pe.test.exchange.application.port.in;

import pe.test.exchange.application.dto.ExchangeRateRequest;
import pe.test.exchange.application.dto.ExchangeRateResponse;

/**
 * Define el caso de uso que ofrece la aplicacion.
 * Permite que el controller dependa de una contrato intermedio y este abstraido.
 */

public interface ExchangeRateUseCase {
    ExchangeRateResponse convert(ExchangeRateRequest request);
}
