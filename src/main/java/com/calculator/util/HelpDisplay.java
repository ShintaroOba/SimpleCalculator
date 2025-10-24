package com.calculator.util;

/**
 * Utility class for displaying help information.
 */
public class HelpDisplay {
    
    private static final String HELP_MESSAGE = 
        "Calculator - A simple command-line calculator\n\n" +
        "Usage:\n" +
        "  calculator <number> <operator> <number>  Perform calculation\n" +
        "  calculator --help                        Show this help message\n" +
        "  calculator --version                     Show version information\n\n" +
        "Operators:\n" +
        "  +    Addition\n" +
        "  -    Subtraction\n" +
        "  *    Multiplication\n" +
        "  /    Division\n\n" +
        "Examples:\n" +
        "  calculator 5 + 3     # Result: 8\n" +
        "  calculator 10.5 - 2  # Result: 8.5\n" +
        "  calculator 4 * 7     # Result: 28\n" +
        "  calculator 15 / 3    # Result: 5\n\n" +
        "Notes:\n" +
        "  - Numbers can be integers or decimals\n" +
        "  - Division by zero is not allowed\n" +
        "  - Use quotes around operators if needed in your shell";
    
    /**
     * Returns the help message.
     * 
     * @return formatted help message
     */
    public String getHelpMessage() {
        return HELP_MESSAGE;
    }
}
