package com.calculator.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DivisionOperation class.
 * Tests division operation functionality and edge cases including division by zero.
 */
@DisplayName("Division Operation Tests")
class DivisionOperationTest {
    
    private DivisionOperation divisionOperation;
    
    @BeforeEach
    void setUp() {
        divisionOperation = new DivisionOperation();
    }
    
    @Test
    @DisplayName("Should return correct symbol")
    void shouldReturnCorrectSymbol() {
        assertEquals("/", divisionOperation.getSymbol());
    }
    
    @Test
    @DisplayName("Should divide two positive integers correctly")
    void shouldDivideTwoPositiveIntegers() {
        double result = divisionOperation.execute(15.0, 3.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should divide positive and negative numbers correctly")
    void shouldDividePositiveAndNegativeNumbers() {
        double result = divisionOperation.execute(10.0, -2.0);
        assertEquals(-5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should divide two negative numbers correctly")
    void shouldDivideTwoNegativeNumbers() {
        double result = divisionOperation.execute(-20.0, -4.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should divide decimal numbers correctly")
    void shouldDivideDecimalNumbers() {
        double result = divisionOperation.execute(7.5, 2.5);
        assertEquals(3.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should divide by one correctly")
    void shouldDivideByOneCorrectly() {
        double result = divisionOperation.execute(5.0, 1.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should divide zero by a number correctly")
    void shouldDivideZeroByNumberCorrectly() {
        double result = divisionOperation.execute(0.0, 5.0);
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should throw ArithmeticException when dividing by zero")
    void shouldThrowExceptionWhenDividingByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(5.0, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw ArithmeticException when dividing by very small number")
    void shouldThrowExceptionWhenDividingByVerySmallNumber() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(5.0, 1e-11));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should handle fractional results")
    void shouldHandleFractionalResults() {
        double result = divisionOperation.execute(1.0, 3.0);
        assertEquals(0.3333333333333333, result, 0.0000000000000001);
    }
    
    @Test
    @DisplayName("Should handle large numbers")
    void shouldHandleLargeNumbers() {
        double result = divisionOperation.execute(2000000.0, 1000.0);
        assertEquals(2000.0, result, 0.001);
    }
}
