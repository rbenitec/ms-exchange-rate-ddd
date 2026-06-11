package pe.test.exchange.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import pe.test.exchange.domain.model.CurrencyCode;
import pe.test.exchange.domain.model.ExchangeRate;
import pe.test.exchange.infrastructure.persistence.entity.ExchangeRateEntity;

@Component
public class ExchangePersistenceMapper {
    public ExchangeRateEntity toEntity(ExchangeRate exchangeRate) {
        return new ExchangeRateEntity(
                exchangeRate.getId(),
                exchangeRate.getAmount(),
                exchangeRate.getSourceCurrency().value(),
                exchangeRate.getTargetCurrency().value(),
                exchangeRate.getExchangeRate(),
                exchangeRate.getConvertedAmount(),
                exchangeRate.getOperationDate()
        );
    }

    public ExchangeRate toDomain(ExchangeRateEntity entity) {
        return new ExchangeRate(
                entity.getId(),
                entity.getAmount(),
                new CurrencyCode(entity.getSourceCurrency()),
                new CurrencyCode(entity.getTargetCurrency()),
                entity.getExchangeRate(),
                entity.getConvertedAmount(),
                entity.getOperationDate()
        );
    }

}
