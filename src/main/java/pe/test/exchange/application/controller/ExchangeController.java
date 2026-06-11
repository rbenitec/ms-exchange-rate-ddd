package pe.test.exchange.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.test.exchange.application.dto.ExchangeRateRequest;
import pe.test.exchange.application.dto.ExchangeRateResponse;
import pe.test.exchange.application.port.in.ExchangeRateUseCase;

@RestController
@RequestMapping("/api/v1/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    /**
     * Expone los endpoint REST tipo POST y delegara la lógica al caso de uso.
     * En este caso no incluira reglas de negocio.
     */
    private final ExchangeRateUseCase useCase;

    @PostMapping("/convert")
    public ResponseEntity<ExchangeRateResponse> convert(
            @RequestBody @Valid ExchangeRateRequest request
    ) {
        return ResponseEntity.ok(useCase.convert(request));
    }
}
