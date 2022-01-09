package ir.maktab.homeServiceProvider.exception;

public class NotFoundDta extends RuntimeException {
    private int errorCode;

    public NotFoundDta(String message) {
        super(message);
    }
}
