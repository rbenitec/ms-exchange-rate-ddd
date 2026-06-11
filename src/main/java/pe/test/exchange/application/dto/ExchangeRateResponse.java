package pe.test.exchange.application.dto;

import java.math.BigDecimal;

public record ExchangeRateResponse (
        BigDecimal monto,
        BigDecimal montoConvertido,
        String monedaOrigen,
        String monedaDestino,
        BigDecimal tipoCambio
) {}