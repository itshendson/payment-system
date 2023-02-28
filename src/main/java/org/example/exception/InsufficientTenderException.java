package org.example.exception;

/**
 * Exception for situation where the payment system does not have enough change to give customers.
 */
public class InsufficientTenderException extends RuntimeException {
    private String message;

    public InsufficientTenderException(String s) {
        this.message = s;
    }
}
