package ir.maktab.homeServiceProvider.service.exception;

public class DuplicateData extends RuntimeException {
    private int errorCode;

    public DuplicateData(String message) {
        super(message);
    }
}
