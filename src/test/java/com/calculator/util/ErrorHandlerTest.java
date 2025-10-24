package com.calculator.util;

import com.calculator.exception.InvalidArgumentException;
import com.calculator.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ErrorHandler class.
 * Tests error message formatting and handling for different exception types.
 */
@DisplayName("Error Handler Tests")
class ErrorHandlerTest {
    
    private ErrorHandler errorHandler;
    
    @BeforeEach
    void setUp() {
        errorHandler = new ErrorHandler();
    }
    
    @Test
    @DisplayName("Should handle InvalidArgumentException correctly")
    void shouldHandleInvalidArgumentException() {
        InvalidArgumentException exception = new InvalidArgumentException("Invalid arguments provided");
        String result = errorHandler.handleError(exception);
        
        assertTrue(result.contains("Error: Invalid arguments provided"));
        assertTrue(result.contains("Usage: calculator"));
        assertTrue(result.contains("Operators: +, -, *, /"));
        assertTrue(result.contains("Examples:"));
    }
    
    @Test
    @DisplayName("Should handle InvalidInputException correctly")
    void shouldHandleInvalidInputException() {
        InvalidInputException exception = new InvalidInputException("Invalid input detected");
        String result = errorHandler.handleError(exception);
        
        assertEquals("Error: Invalid input detected", result);
    }
    
    @Test
    @DisplayName("Should handle ArithmeticException correctly")
    void shouldHandleArithmeticException() {
        ArithmeticException exception = new ArithmeticException("Division by zero is not allowed");
        String result = errorHandler.handleError(exception);
        
        assertEquals("Error: Division by zero is not allowed", result);
    }
    
    @Test
    @DisplayName("Should handle NumberFormatException correctly")
    void shouldHandleNumberFormatException() {
        NumberFormatException exception = new NumberFormatException("For input string: \"abc\"");
        String result = errorHandler.handleError(exception);
        
        assertTrue(result.contains("Error: Invalid number format"));
        assertTrue(result.contains("Usage: calculator"));
        assertTrue(result.contains("Examples:"));
    }
    
    @Test
    @DisplayName("Should handle generic Exception correctly")
    void shouldHandleGenericException() {
        RuntimeException exception = new RuntimeException("Unexpected error occurred");
        String result = errorHandler.handleError(exception);
        
        assertEquals("Error: An unexpected error occurred: Unexpected error occurred", result);
    }
    
    @Test
    @DisplayName("Should handle exception with null message")
    void shouldHandleExceptionWithNullMessage() {
        ArithmeticException exception = new ArithmeticException();
        String result = errorHandler.handleError(exception);
        
        assertTrue(result.contains("Error:"));
    }
    
    @Test
    @DisplayName("Should return proper usage message")
    void shouldReturnProperUsageMessage() {
        String usage = errorHandler.getUsageMessage();
        
        assertTrue(usage.contains("Usage: calculator <number> <operator> <number>"));
        assertTrue(usage.contains("calculator --help"));
        assertTrue(usage.contains("calculator --version"));
        assertTrue(usage.contains("Operators: +, -, *, /"));
        assertTrue(usage.contains("Examples:"));
        assertTrue(usage.contains("calculator 5 + 3"));
        assertTrue(usage.contains("calculator 10.5 - 2.3"));
        assertTrue(usage.contains("calculator 4 * 7"));
        assertTrue(usage.contains("calculator 15 / 3"));
    }
    
    @Test
    @DisplayName("Should format InvalidArgumentException with usage info")
    void shouldFormatInvalidArgumentExceptionWithUsage() {
        InvalidArgumentException exception = new InvalidArgumentException("No arguments provided");
        String result = errorHandler.handleError(exception);
        
        String[] lines = result.split("\n");
        assertTrue(lines[0].contains("Error: No arguments provided"));
        assertTrue(result.contains("Usage: calculator"));
    }
    
    @Test
    @DisplayName("Should handle InvalidInputException without usage info")
    void shouldHandleInvalidInputExceptionWithoutUsage() {
        InvalidInputException exception = new InvalidInputException("Division by zero is not allowed");
        String result = errorHandler.handleError(exception);
        
        assertEquals("Error: Division by zero is not allowed", result);
        assertFalse(result.contains("Usage:"));
    }
    
    @Test
    @DisplayName("Should handle NumberFormatException with usage info")
    void shouldHandleNumberFormatExceptionWithUsage() {
        NumberFormatException exception = new NumberFormatException("Invalid number");
        String result = errorHandler.handleError(exception);
        
        assertTrue(result.contains("Error: Invalid number format"));
        assertTrue(result.contains("Usage: calculator"));
    }
    
    @Test
    @DisplayName("Should preserve original exception message in error output")
    void shouldPreserveOriginalExceptionMessage() {
        String originalMessage = "Custom error message for testing";
        InvalidInputException exception = new InvalidInputException(originalMessage);
        String result = errorHandler.handleError(exception);
        
        assertTrue(result.contains(originalMessage));
    }
}
