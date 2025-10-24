package com.calculator.util;

import com.calculator.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ArgumentParser class.
 * Tests command line argument parsing for calculator operations and special flags.
 */
@DisplayName("Argument Parser Tests")
class ArgumentParserTest {
    
    private ArgumentParser argumentParser;
    
    @BeforeEach
    void setUp() {
        argumentParser = new ArgumentParser();
    }
    
    @Test
    @DisplayName("Should parse valid calculation arguments")
    void shouldParseValidCalculationArguments() throws InvalidArgumentException {
        String[] args = {"5", "+", "3"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertEquals(5.0, result.getLeftOperand(), 0.001);
        assertEquals("+", result.getOperator());
        assertEquals(3.0, result.getRightOperand(), 0.001);
        assertFalse(result.isHelpRequest());
        assertFalse(result.isVersionRequest());
    }
    
    @Test
    @DisplayName("Should parse decimal numbers correctly")
    void shouldParseDecimalNumbers() throws InvalidArgumentException {
        String[] args = {"10.5", "-", "2.3"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertEquals(10.5, result.getLeftOperand(), 0.001);
        assertEquals("-", result.getOperator());
        assertEquals(2.3, result.getRightOperand(), 0.001);
    }
    
    @Test
    @DisplayName("Should parse negative numbers correctly")
    void shouldParseNegativeNumbers() throws InvalidArgumentException {
        String[] args = {"-5", "*", "-3"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertEquals(-5.0, result.getLeftOperand(), 0.001);
        assertEquals("*", result.getOperator());
        assertEquals(-3.0, result.getRightOperand(), 0.001);
    }
    
    @Test
    @DisplayName("Should parse help flag")
    void shouldParseHelpFlag() throws InvalidArgumentException {
        String[] args = {"--help"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertTrue(result.isHelpRequest());
        assertFalse(result.isVersionRequest());
    }
    
    @Test
    @DisplayName("Should parse short help flag")
    void shouldParseShortHelpFlag() throws InvalidArgumentException {
        String[] args = {"-h"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertTrue(result.isHelpRequest());
        assertFalse(result.isVersionRequest());
    }
    
    @Test
    @DisplayName("Should parse version flag")
    void shouldParseVersionFlag() throws InvalidArgumentException {
        String[] args = {"--version"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertFalse(result.isHelpRequest());
        assertTrue(result.isVersionRequest());
    }
    
    @Test
    @DisplayName("Should parse short version flag")
    void shouldParseShortVersionFlag() throws InvalidArgumentException {
        String[] args = {"-v"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertFalse(result.isHelpRequest());
        assertTrue(result.isVersionRequest());
    }
    
    @Test
    @DisplayName("Should throw exception for null arguments")
    void shouldThrowExceptionForNullArguments() {
        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, 
            () -> argumentParser.parse(null));
        assertEquals("No arguments provided. Use --help for usage information.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for empty arguments")
    void shouldThrowExceptionForEmptyArguments() {
        String[] args = {};
        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, 
            () -> argumentParser.parse(args));
        assertEquals("No arguments provided. Use --help for usage information.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for too few arguments")
    void shouldThrowExceptionForTooFewArguments() {
        String[] args = {"5", "+"};
        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, 
            () -> argumentParser.parse(args));
        assertEquals("Invalid number of arguments. Expected format: <number> <operator> <number>", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for too many arguments")
    void shouldThrowExceptionForTooManyArguments() {
        String[] args = {"5", "+", "3", "extra"};
        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, 
            () -> argumentParser.parse(args));
        assertEquals("Invalid number of arguments. Expected format: <number> <operator> <number>", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for invalid number format")
    void shouldThrowExceptionForInvalidNumberFormat() {
        String[] args = {"abc", "+", "3"};
        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, 
            () -> argumentParser.parse(args));
        assertTrue(exception.getMessage().contains("Invalid number format"));
    }
    
    @Test
    @DisplayName("Should throw exception for invalid second number")
    void shouldThrowExceptionForInvalidSecondNumber() {
        String[] args = {"5", "+", "xyz"};
        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, 
            () -> argumentParser.parse(args));
        assertTrue(exception.getMessage().contains("Invalid number format"));
    }
    
    @Test
    @DisplayName("Should handle zero values correctly")
    void shouldHandleZeroValues() throws InvalidArgumentException {
        String[] args = {"0", "*", "5"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertEquals(0.0, result.getLeftOperand(), 0.001);
        assertEquals("*", result.getOperator());
        assertEquals(5.0, result.getRightOperand(), 0.001);
    }
    
    @Test
    @DisplayName("Should handle large numbers correctly")
    void shouldHandleLargeNumbers() throws InvalidArgumentException {
        String[] args = {"1000000", "/", "2000000"};
        ArgumentParser.ParsedArguments result = argumentParser.parse(args);
        
        assertEquals(1000000.0, result.getLeftOperand(), 0.001);
        assertEquals("/", result.getOperator());
        assertEquals(2000000.0, result.getRightOperand(), 0.001);
    }
}
