package ir.maktab.homeServiceProvider.service.exception;

public class NotFoundDta extends RuntimeException {
    private int errorCode;

    public NotFoundDta(String message) {
        super(message);
    }
}
