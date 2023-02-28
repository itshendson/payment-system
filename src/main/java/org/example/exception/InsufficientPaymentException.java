package org.example.exception;

/**
 * Exception for situation where the customer has not made sufficient payment for a product.
 */
public class InsufficientPaymentException extends RuntimeException {
    private String message;

    public InsufficientPaymentException(String s) {
        this.message = s;
    }
}
