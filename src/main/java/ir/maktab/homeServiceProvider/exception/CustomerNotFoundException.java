package ir.maktab.homeServiceProvider.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException() {
        super("customer not found");
    }

}
