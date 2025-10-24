package com.calculator.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AdditionOperation class.
 * Tests addition operation functionality and edge cases.
 */
@DisplayName("Addition Operation Tests")
class AdditionOperationTest {
    
    private AdditionOperation additionOperation;
    
    @BeforeEach
    void setUp() {
        additionOperation = new AdditionOperation();
    }
    
    @Test
    @DisplayName("Should return correct symbol")
    void shouldReturnCorrectSymbol() {
        assertEquals("+", additionOperation.getSymbol());
    }
    
    @Test
    @DisplayName("Should add two positive integers correctly")
    void shouldAddTwoPositiveIntegers() {
        double result = additionOperation.execute(5.0, 3.0);
        assertEquals(8.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should add positive and negative numbers correctly")
    void shouldAddPositiveAndNegativeNumbers() {
        double result = additionOperation.execute(10.0, -3.0);
        assertEquals(7.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should add two negative numbers correctly")
    void shouldAddTwoNegativeNumbers() {
        double result = additionOperation.execute(-5.0, -3.0);
        assertEquals(-8.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should add decimal numbers correctly")
    void shouldAddDecimalNumbers() {
        double result = additionOperation.execute(2.5, 3.7);
        assertEquals(6.2, result, 0.001);
    }
    
    @Test
    @DisplayName("Should add zero correctly")
    void shouldAddZeroCorrectly() {
        double result = additionOperation.execute(5.0, 0.0);
        assertEquals(5.0, result, 0.001);
        
        result = additionOperation.execute(0.0, 5.0);
        assertEquals(5.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle large numbers")
    void shouldHandleLargeNumbers() {
        double result = additionOperation.execute(1000000.0, 2000000.0);
        assertEquals(3000000.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should handle very small decimal numbers")
    void shouldHandleVerySmallDecimalNumbers() {
        double result = additionOperation.execute(0.0001, 0.0002);
        assertEquals(0.0003, result, 0.00001);
    }
}
