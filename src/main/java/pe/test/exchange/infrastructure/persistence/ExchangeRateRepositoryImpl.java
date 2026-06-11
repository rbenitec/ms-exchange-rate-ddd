package pe.test.exchange.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.test.exchange.domain.model.ExchangeRate;
import pe.test.exchange.domain.repository.ExchangeRateRepository;
import pe.test.exchange.infrastructure.persistence.entity.ExchangeRateEntity;
import pe.test.exchange.infrastructure.persistence.mapper.ExchangePersistenceMapper;
import pe.test.exchange.infrastructure.persistence.repository.JpaExchangeRateRepository;


/**
 * Implementación del repositorio, se convierte el modelo de negocio a entidad JPA y guarda en la base de datos.
 */

@Component
@RequiredArgsConstructor
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
    private final JpaExchangeRateRepository jpaRepository;
    private final ExchangePersistenceMapper mapper;

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {

        ExchangeRateEntity entity = mapper.toEntity(exchangeRate);
        return mapper.toDomain(jpaRepository.save(entity));
    }
}
