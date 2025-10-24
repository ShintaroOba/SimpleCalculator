package com.calculator.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MultiplicationOperation class.
 * Tests multiplication operation functionality and edge cases.
 */
@DisplayName("Multiplication Operation Tests")
class MultiplicationOperationTest {
    
    private MultiplicationOperation multiplicationOperation;
    
    @BeforeEach
    void setUp() {
        multiplicationOperation = new MultiplicationOperation();
    }
    
    @Test
    @DisplayName("Should return correct symbol")
    void shouldReturnCorrectSymbol() {
        assertEquals("*", multiplicationOperation.getSymbol());
    }
    
    @Test
    @DisplayName("Should multiply two positive integers correctly")
    void shouldMultiplyTwoPositiveIntegers() {
        double result = multiplicationOperation.execute(6.0, 7.0);
        assertEquals(42.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should multiply positive and negative numbers correctly")
    void shouldMultiplyPositiveAndNegativeNumbers() {
        double result = multiplicationOperation.execute(5.0, -3.0);
        assertEquals(-15.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should multiply two negative numbers correctly")
    void shouldMultiplyTwoNegativeNumbers() {
        double result = multiplicationOperation.execute(-4.0, -5.0);
        assertEquals(20.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should multiply decimal numbers correctly")
    void shouldMultiplyDecimalNumbers() {
        double result = multiplicationOperation.execute(2.5, 4.0);
        assertEquals(10.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should multiply by zero correctly")
    void shouldMultiplyByZeroCorrectly() {
        double result = multiplicationOperation.execute(5.0, 0.0);
        assertEquals(0.0, result, 0.001);
        
        result = multiplicationOperation.execute(0.0, 5.0);
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should multiply by one correctly")
    void shouldMultiplyByOneCorrectly() {
        double result = multiplicationOperation.execute(5.0, 1.0);
        assertEquals(5.0, result, 0.001);
        
        result = multiplicationOperation.execute(1.0, 5.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle large numbers")
    void shouldHandleLargeNumbers() {
        double result = multiplicationOperation.execute(1000.0, 2000.0);
        assertEquals(2000000.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle fractional results")
    void shouldHandleFractionalResults() {
        double result = multiplicationOperation.execute(0.5, 0.5);
        assertEquals(0.25, result, 0.001);
    }
}
