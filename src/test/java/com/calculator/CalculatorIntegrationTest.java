package com.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Calculator class.
 * Tests the complete calculation workflow with all operations.
 */
@DisplayName("Calculator Integration Tests")
class CalculatorIntegrationTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Should perform addition calculation")
    void shouldPerformAdditionCalculation() {
        double result = calculator.calculate(5.0, "+", 3.0);
        assertEquals(8.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should perform subtraction calculation")
    void shouldPerformSubtractionCalculation() {
        double result = calculator.calculate(10.0, "-", 4.0);
        assertEquals(6.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should perform multiplication calculation")
    void shouldPerformMultiplicationCalculation() {
        double result = calculator.calculate(6.0, "*", 7.0);
        assertEquals(42.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should perform division calculation")
    void shouldPerformDivisionCalculation() {
        double result = calculator.calculate(15.0, "/", 3.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle decimal numbers in calculations")
    void shouldHandleDecimalNumbers() {
        double result = calculator.calculate(10.5, "+", 2.3);
        assertEquals(12.8, result, 0.001);
        
        result = calculator.calculate(7.5, "-", 2.5);
        assertEquals(5.0, result, 0.001);
        
        result = calculator.calculate(2.5, "*", 4.0);
        assertEquals(10.0, result, 0.001);
        
        result = calculator.calculate(7.5, "/", 2.5);
        assertEquals(3.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle negative numbers in calculations")
    void shouldHandleNegativeNumbers() {
        double result = calculator.calculate(-5.0, "+", 3.0);
        assertEquals(-2.0, result, 0.001);
        
        result = calculator.calculate(5.0, "-", -3.0);
        assertEquals(8.0, result, 0.001);
        
        result = calculator.calculate(-4.0, "*", -5.0);
        assertEquals(20.0, result, 0.001);
        
        result = calculator.calculate(-15.0, "/", -3.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should throw IllegalArgumentException for unsupported operator")
    void shouldThrowExceptionForUnsupportedOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.calculate(5.0, "^", 3.0));
        assertEquals("Unsupported operator: ^", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw ArithmeticException for division by zero")
    void shouldThrowExceptionForDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> calculator.calculate(5.0, "/", 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should handle operator with whitespace")
    void shouldHandleOperatorWithWhitespace() {
        double result = calculator.calculate(5.0, " + ", 3.0);
        assertEquals(8.0, result, 0.001);
        
        result = calculator.calculate(10.0, " - ", 4.0);
        assertEquals(6.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle zero operands correctly")
    void shouldHandleZeroOperands() {
        double result = calculator.calculate(0.0, "+", 5.0);
        assertEquals(5.0, result, 0.001);
        
        result = calculator.calculate(5.0, "*", 0.0);
        assertEquals(0.0, result, 0.001);
        
        result = calculator.calculate(0.0, "/", 5.0);
        assertEquals(0.0, result, 0.001);
    }
}
