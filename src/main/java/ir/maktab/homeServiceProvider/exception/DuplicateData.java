package ir.maktab.homeServiceProvider.exception;

public class DuplicateData extends RuntimeException {
    private int errorCode;

    public DuplicateData(String message) {
        super(message);
    }
}
