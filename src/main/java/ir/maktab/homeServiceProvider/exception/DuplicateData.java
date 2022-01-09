package ir.maktab.homeServiceProvider.exception;

public class DuplicateData extends Exception {
    private int errorCode;

    public DuplicateData(String message) {
        super(message);
    }
}
