package com.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Application-level integration tests for CalculatorApp.
 * Tests the complete application workflow including output capture.
 */
@DisplayName("Calculator Application Tests")
class CalculatorAppTest {
    
    private CalculatorApp calculatorApp;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    @BeforeEach
    void setUp() {
        calculatorApp = new CalculatorApp();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    @Test
    @DisplayName("Should return 0 for successful calculation")
    void shouldReturnZeroForSuccessfulCalculation() {
        String[] args = {"5", "+", "3"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("8", outputStream.toString().trim());
        assertEquals("", errorStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should perform addition correctly")
    void shouldPerformAdditionCorrectly() {
        String[] args = {"10", "+", "5"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("15", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should perform subtraction correctly")
    void shouldPerformSubtractionCorrectly() {
        String[] args = {"10", "-", "3"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("7", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should perform multiplication correctly")
    void shouldPerformMultiplicationCorrectly() {
        String[] args = {"6", "*", "7"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("42", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should perform division correctly")
    void shouldPerformDivisionCorrectly() {
        String[] args = {"15", "/", "3"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("5", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should handle decimal results correctly")
    void shouldHandleDecimalResults() {
        String[] args = {"7", "/", "2"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("3.5", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should format whole number results without decimals")
    void shouldFormatWholeNumberResults() {
        String[] args = {"8", "/", "2"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("4", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should display help message")
    void shouldDisplayHelpMessage() {
        String[] args = {"--help"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        String output = outputStream.toString();
        assertTrue(output.contains("Calculator - A simple command-line calculator"));
        assertTrue(output.contains("Usage:"));
        assertTrue(output.contains("Examples:"));
    }
    
    @Test
    @DisplayName("Should display version message")
    void shouldDisplayVersionMessage() {
        String[] args = {"--version"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        String output = outputStream.toString();
        assertTrue(output.contains("Calculator version"));
        assertTrue(output.contains("Built with Java"));
    }
    
    @Test
    @DisplayName("Should return 1 for invalid arguments")
    void shouldReturnOneForInvalidArguments() {
        String[] args = {"abc", "+", "5"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(1, exitCode);
        String error = errorStream.toString();
        assertTrue(error.contains("Error: Invalid number format"));
    }
    
    @Test
    @DisplayName("Should return 1 for division by zero")
    void shouldReturnOneForDivisionByZero() {
        String[] args = {"5", "/", "0"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(1, exitCode);
        String error = errorStream.toString();
        assertTrue(error.contains("Error: Division by zero is not allowed"));
    }
    
    @Test
    @DisplayName("Should return 1 for unsupported operator")
    void shouldReturnOneForUnsupportedOperator() {
        String[] args = {"5", "^", "2"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(1, exitCode);
        String error = errorStream.toString();
        assertTrue(error.contains("Error: Unsupported operator"));
    }
    
    @Test
    @DisplayName("Should return 1 for no arguments")
    void shouldReturnOneForNoArguments() {
        String[] args = {};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(1, exitCode);
        String error = errorStream.toString();
        assertTrue(error.contains("Error: No arguments provided"));
        assertTrue(error.contains("Usage:"));
    }
    
    @Test
    @DisplayName("Should handle negative numbers in calculations")
    void shouldHandleNegativeNumbers() {
        String[] args = {"-5", "+", "3"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("-2", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should handle decimal inputs")
    void shouldHandleDecimalInputs() {
        String[] args = {"2.5", "*", "4"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("10", outputStream.toString().trim());
    }
    
    @Test
    @DisplayName("Should handle zero operands")
    void shouldHandleZeroOperands() {
        String[] args = {"0", "+", "5"};
        int exitCode = calculatorApp.run(args);
        
        assertEquals(0, exitCode);
        assertEquals("5", outputStream.toString().trim());
    }
}
