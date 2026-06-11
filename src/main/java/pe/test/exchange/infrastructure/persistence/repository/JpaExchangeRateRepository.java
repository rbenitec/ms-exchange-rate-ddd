package pe.test.exchange.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.test.exchange.infrastructure.persistence.entity.ExchangeRateEntity;

public interface JpaExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
}
