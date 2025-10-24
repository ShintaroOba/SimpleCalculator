package com.calculator.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SubtractionOperation class.
 * Tests subtraction operation functionality and edge cases.
 */
@DisplayName("Subtraction Operation Tests")
class SubtractionOperationTest {
    
    private SubtractionOperation subtractionOperation;
    
    @BeforeEach
    void setUp() {
        subtractionOperation = new SubtractionOperation();
    }
    
    @Test
    @DisplayName("Should return correct symbol")
    void shouldReturnCorrectSymbol() {
        assertEquals("-", subtractionOperation.getSymbol());
    }
    
    @Test
    @DisplayName("Should subtract two positive integers correctly")
    void shouldSubtractTwoPositiveIntegers() {
        double result = subtractionOperation.execute(10.0, 3.0);
        assertEquals(7.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should subtract positive and negative numbers correctly")
    void shouldSubtractPositiveAndNegativeNumbers() {
        double result = subtractionOperation.execute(10.0, -3.0);
        assertEquals(13.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should subtract two negative numbers correctly")
    void shouldSubtractTwoNegativeNumbers() {
        double result = subtractionOperation.execute(-5.0, -3.0);
        assertEquals(-2.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should subtract decimal numbers correctly")
    void shouldSubtractDecimalNumbers() {
        double result = subtractionOperation.execute(5.7, 2.3);
        assertEquals(3.4, result, 0.001);
    }
    
    @Test
    @DisplayName("Should subtract zero correctly")
    void shouldSubtractZeroCorrectly() {
        double result = subtractionOperation.execute(5.0, 0.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should subtract from zero correctly")
    void shouldSubtractFromZeroCorrectly() {
        double result = subtractionOperation.execute(0.0, 5.0);
        assertEquals(-5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle large numbers")
    void shouldHandleLargeNumbers() {
        double result = subtractionOperation.execute(5000000.0, 2000000.0);
        assertEquals(3000000.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle result of zero")
    void shouldHandleResultOfZero() {
        double result = subtractionOperation.execute(5.0, 5.0);
        assertEquals(0.0, result, 0.001);
    }
}
