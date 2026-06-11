package pe.test.exchange.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import pe.test.exchange.shared.constants.ExchangeConstants;

import java.math.BigDecimal;

/**
 * DTOs usados para recibir y responder datos desde la API, Validando el formato correspondiente.
 * Protegen al dominio de depender del contrato REST.
 * @param monto
 * @param monedaOrigen
 * @param monedaDestino
 */
public record ExchangeRateRequest(
        @NotNull(message = "1: No debe ser nulo")
        @DecimalMax(value = "10000.00", message = "2: El monto no debe ser mayor a 10000.00")
        @DecimalMin(value = "1.00", message = "3: El monto no debe ser menor a 1.00")
        @Digits(integer = 5, fraction = 2, message = "4: El monto debe tener hasta 5 dígitos enteros y 2 decimales")
        BigDecimal monto,
        @NotNull(message = "1: No debe ser nulo")
        @NotBlank(message = "2: No debe ser vacio")
        @Size(min = 3, max = 3, message = "3: La moneda debe tener exactamente 3 caracteres")
        @Pattern(regexp = ExchangeConstants.PATTERN_CURRENCY,
                message = "4: La moneda debe estar en mayúsculas y contener solo letras")
        String monedaOrigen,
        @NotNull(message = "1: No debe ser nulo")
        @NotBlank(message = "2: No debe ser vacio")
        @Size(min = 3, max = 3, message = "3: La moneda debe tener exactamente 3 caracteres")
        @Pattern(regexp = ExchangeConstants.PATTERN_CURRENCY,
                message = "4: La moneda debe estar en mayúsculas y contener solo letras")
        String monedaDestino
) {

}
