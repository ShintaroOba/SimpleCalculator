package com.calculator.exception;

/**
 * Exception thrown when input values are invalid for calculations.
 */
public class InvalidInputException extends Exception {
    
    /**
     * Constructs an InvalidInputException with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidInputException(String message) {
        super(message);
    }
    
    /**
     * Constructs an InvalidInputException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
