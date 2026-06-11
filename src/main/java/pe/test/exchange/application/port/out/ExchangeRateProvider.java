package pe.test.exchange.application.port.out;

import java.math.BigDecimal;

/**
 * Define una salida necesaria del caso de uso hacia un servicio externo.
 * La capa aplicacion, no conoce Feign ni detalles tecnico
 */
public interface ExchangeRateProvider {
    BigDecimal getRate(String origin, String destination);
}
