package pe.test.exchange.application.mapper;

import org.springframework.stereotype.Component;
import pe.test.exchange.application.dto.ExchangeRateResponse;
import pe.test.exchange.domain.model.ExchangeRate;

import java.util.function.Function;

/**
 * Convierte objetos entre la capa application y el dominio.
 * Evita mezclar armado de respuestas dentro del service.
 */
@Component
public class ExchangeApplicationMapper implements Function<ExchangeRate, ExchangeRateResponse> {

    @Override
    public ExchangeRateResponse apply(ExchangeRate exchangeRate) {
        return new ExchangeRateResponse(
                exchangeRate.getAmount(),
                exchangeRate.getConvertedAmount(),
                exchangeRate.getSourceCurrency().value(),
                exchangeRate.getTargetCurrency().value(),
                exchangeRate.getExchangeRate()
        );
    }
}