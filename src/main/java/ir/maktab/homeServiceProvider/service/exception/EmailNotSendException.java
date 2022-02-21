package ir.maktab.homeServiceProvider.service.exception;

import javax.mail.MessagingException;

public class EmailNotSendException extends MessagingException {
    public EmailNotSendException(String message) {
        super(message);
    }
}
