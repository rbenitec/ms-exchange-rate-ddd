package pe.test.exchange.infrastructure.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.test.exchange.application.port.out.ExchangeRateProvider;
import pe.test.exchange.domain.exception.ExchangeRateNotFoundException;
import pe.test.exchange.infrastructure.external.dto.ExchangeRateApiResponse;

import java.math.BigDecimal;

/**
 * Implementación de la api de tipo de cambio para devolver la tasa requerida por el caso de uso, en nuestro domain
 */
@Component
@RequiredArgsConstructor
public class ExchangeRateProviderImpl implements ExchangeRateProvider {
    private final ExchangeRateApiClient client;

    @Value("${exchange-rate.api.key}")
    private String apiKey;

    @Override
    public BigDecimal getRate(String sourceCurrency, String targetCurrency) {
        ExchangeRateApiResponse response = client.getLatestRates(apiKey, sourceCurrency);

        BigDecimal rate = response.conversionRates().get(targetCurrency);

        if (rate == null) {
            throw new ExchangeRateNotFoundException(
                    "No existe tipo de cambio para " + targetCurrency,
                    "ERR_NOT_FOUND"
            );
        }

        return rate;
    }
}
