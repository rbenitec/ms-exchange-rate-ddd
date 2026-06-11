package pe.test.exchange.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.test.exchange.application.dto.ExchangeRateRequest;
import pe.test.exchange.application.dto.ExchangeRateResponse;
import pe.test.exchange.application.mapper.ExchangeApplicationMapper;
import pe.test.exchange.application.port.in.ExchangeRateUseCase;
import pe.test.exchange.domain.model.CurrencyCode;
import pe.test.exchange.domain.model.CurrencyPair;
import pe.test.exchange.domain.model.ExchangeRate;
import pe.test.exchange.domain.repository.ExchangeRateRepository;
import pe.test.exchange.domain.service.ExchangeCalculatedService;
import pe.test.exchange.infrastructure.external.ExchangeRateProviderImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateUseCase {

    private final ExchangeRateProviderImpl exchangeRateProviderImpl;
    private final ExchangeCalculatedService calculatedService;
    private final ExchangeRateRepository repository;
    private final ExchangeApplicationMapper mapper;

    @Override
    public ExchangeRateResponse convert(ExchangeRateRequest request) {

        /**
         * Esta parte es necesaria para cumplir con DDD
         * para poder convertir datos simples del request rn un value Object de dominio
         * con el objetivo de validar los datos y evitar errores en el dominio
         */

        CurrencyCode source = new CurrencyCode(request.monedaOrigen());
        CurrencyCode target = new CurrencyCode(request.monedaDestino());
        CurrencyPair currencyPair = new CurrencyPair(source, target);

        /**
         * Realizo la orquestación de la lógica de negocio, primero obtengo el tipo de cambio desde el proveedor externo
         * luego calculo el monto convertido utilizando el servicio de cálculo, finalmente creo una entidad de tipo ExchangeRate
         * con los datos obtenidos y la guardo en el repositorio para luego retornar la respuesta al cliente
         *
         * Aqui no deberia de contener detalles tecnico, el cual e s trabjo de la capa de infraestructura
         */
        BigDecimal rate = exchangeRateProviderImpl.getRate(
                currencyPair.source().value(),
                currencyPair.target().value()
                );

        BigDecimal convertedAmount = calculatedService.calculate(request.monto(), rate);

        ExchangeRate conversion = new ExchangeRate(
                null,
                request.monto(),
                source,
                target,
                rate,
                convertedAmount,
                LocalDateTime.now()
        );

        ExchangeRate savedConversion = repository.save(conversion);
        return mapper.apply(savedConversion);
    }
}
