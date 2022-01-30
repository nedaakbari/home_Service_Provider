package ir.maktab.homeServiceProvider.service.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("user not found");
    }

}
