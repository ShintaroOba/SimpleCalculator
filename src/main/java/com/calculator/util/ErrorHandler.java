package com.calculator.util;

import com.calculator.exception.InvalidArgumentException;
import com.calculator.exception.InvalidInputException;

/**
 * Utility class for handling and formatting errors.
 * Provides consistent error messages and handling across the application.
 */
public class ErrorHandler {
    
    private static final String USAGE_MESSAGE = 
        "Usage: calculator <number> <operator> <number>\n" +
        "       calculator --help\n" +
        "       calculator --version\n\n" +
        "Operators: +, -, *, /\n" +
        "Examples:\n" +
        "  calculator 5 + 3\n" +
        "  calculator 10.5 - 2.3\n" +
        "  calculator 4 * 7\n" +
        "  calculator 15 / 3";
    
    /**
     * Handles exceptions and returns appropriate error messages.
     * 
     * @param throwable the exception to handle
     * @return formatted error message
     */
    public String handleError(Throwable throwable) {
        if (throwable instanceof InvalidArgumentException) {
            return formatArgumentError((InvalidArgumentException) throwable);
        } else if (throwable instanceof InvalidInputException) {
            return formatInputError((InvalidInputException) throwable);
        } else if (throwable instanceof ArithmeticException) {
            return formatArithmeticError((ArithmeticException) throwable);
        } else if (throwable instanceof NumberFormatException) {
            return formatNumberFormatError((NumberFormatException) throwable);
        } else {
            return formatGenericError(throwable);
        }
    }
    
    /**
     * Formats argument-related errors.
     * 
     * @param exception the argument exception
     * @return formatted error message
     */
    private String formatArgumentError(InvalidArgumentException exception) {
        return "Error: " + exception.getMessage() + "\n\n" + USAGE_MESSAGE;
    }
    
    /**
     * Formats input validation errors.
     * 
     * @param exception the input exception
     * @return formatted error message
     */
    private String formatInputError(InvalidInputException exception) {
        return "Error: " + exception.getMessage();
    }
    
    /**
     * Formats arithmetic errors (like division by zero).
     * 
     * @param exception the arithmetic exception
     * @return formatted error message
     */
    private String formatArithmeticError(ArithmeticException exception) {
        return "Error: " + exception.getMessage();
    }
    
    /**
     * Formats number format errors.
     * 
     * @param exception the number format exception
     * @return formatted error message
     */
    private String formatNumberFormatError(NumberFormatException exception) {
        return "Error: Invalid number format. Please provide valid numbers.\n\n" + USAGE_MESSAGE;
    }
    
    /**
     * Formats generic errors.
     * 
     * @param throwable the generic exception
     * @return formatted error message
     */
    private String formatGenericError(Throwable throwable) {
        return "Error: An unexpected error occurred: " + throwable.getMessage();
    }
    
    /**
     * Returns the usage message.
     * 
     * @return usage instructions
     */
    public String getUsageMessage() {
        return USAGE_MESSAGE;
    }
}
