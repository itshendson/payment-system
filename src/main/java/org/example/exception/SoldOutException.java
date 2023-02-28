package org.example.exception;

/**
 * Handle situation where the customer tried to select a product that is not available.
 */
public class SoldOutException extends RuntimeException {
    private String message;

    public SoldOutException(String s) {
        this.message = s;
    }
}
