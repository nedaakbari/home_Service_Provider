package ir.maktab.homeServiceProvider.service.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException() {
        super("customer not found");
    }

}
