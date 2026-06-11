package pe.test.exchange.infrastructure.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.test.exchange.domain.exception.ExchangeBusinessException;
import pe.test.exchange.domain.exception.ExchangeRateNotFoundException;
import pe.test.exchange.domain.exception.ExternalServiceException;
import pe.test.exchange.shared.constants.ExchangeConstants;
import pe.test.exchange.shared.response.ApiErrorResponse;

import java.util.Comparator;
import java.util.Optional;

/**
 * Manejp global de errores
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExchangeRateNotFoundException.class)
    public ResponseEntity<?> handleBusinessException(ExchangeRateNotFoundException ex) {
        return ResponseEntity.accepted()
                .body(new ApiErrorResponse(ExchangeConstants.STATUS_ACCEPTED, ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(ExchangeBusinessException.class)
    public ResponseEntity<?> handleBusinessException(ExchangeBusinessException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiErrorResponse(ExchangeConstants.STATUS_ERROR, ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<?> handleExternalServiceException(ExternalServiceException ex) {
        return ResponseEntity.internalServerError()
                .body(new ApiErrorResponse(ExchangeConstants.STATUS_ERROR, ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new ApiErrorResponse(ExchangeConstants.INTERNAL_ERROR_SERVER, "WS-ERR-500", ex.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String message = ex.getFieldErrors()
                .stream()
                .sorted(Comparator.comparing(fe -> Optional.of(fe.getDefaultMessage()).orElse("")))
                .map(fieldError -> fieldError.getField() + ": " + validateMessage(Optional.of(fieldError.getDefaultMessage()).orElse("Mensaje no disponible")))
                .findFirst()
                .orElse("No hay errores de validacion");

        return ResponseEntity.badRequest()
                .body(new ApiErrorResponse(ExchangeConstants.STATUS_BAD_REQUEST, "WS-ERR-400", message));
    }


    private String validateMessage (String message) {
        if(Character.isDigit(message.charAt(0))) {
            return message.substring(2);
        }
        return message;
    }

}
