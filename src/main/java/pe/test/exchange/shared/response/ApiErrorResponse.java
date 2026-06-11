package pe.test.exchange.shared.response;

public record ApiErrorResponse(
        String status,
        String code,
        String message
) {

}
