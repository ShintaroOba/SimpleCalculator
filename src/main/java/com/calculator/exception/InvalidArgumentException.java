package com.calculator.exception;

/**
 * Exception thrown when command line arguments are invalid.
 */
public class InvalidArgumentException extends Exception {
    
    /**
     * Constructs an InvalidArgumentException with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
    
    /**
     * Constructs an InvalidArgumentException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
