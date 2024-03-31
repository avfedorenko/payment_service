package faang.school.paymentservice.exception;

public class NoSuchCurrencyException extends RuntimeException {
    public NoSuchCurrencyException(String message) {
        super(message);
    }
}
