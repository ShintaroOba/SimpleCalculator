package com.calculator.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Focused tests for division by zero scenarios.
 * Tests edge cases and error handling for division operations.
 */
@DisplayName("Division by Zero Tests")
class DivisionByZeroTest {
    
    private DivisionOperation divisionOperation;
    
    @BeforeEach
    void setUp() {
        divisionOperation = new DivisionOperation();
    }
    
    @Test
    @DisplayName("Should throw ArithmeticException for exact zero divisor")
    void shouldThrowExceptionForExactZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(10.0, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw ArithmeticException for negative zero divisor")
    void shouldThrowExceptionForNegativeZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(10.0, -0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {1e-11, 1e-12, 1e-15, -1e-11, -1e-12, -1e-15})
    @DisplayName("Should throw ArithmeticException for very small divisors")
    void shouldThrowExceptionForVerySmallDivisors(double divisor) {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(10.0, divisor));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {1e-9, 1e-8, 0.001, -1e-9, -1e-8, -0.001})
    @DisplayName("Should NOT throw exception for acceptably small but non-zero divisors")
    void shouldNotThrowExceptionForAcceptablySmallDivisors(double divisor) {
        assertDoesNotThrow(() -> divisionOperation.execute(10.0, divisor));
    }
    
    @Test
    @DisplayName("Should handle zero dividend correctly")
    void shouldHandleZeroDividend() {
        double result = divisionOperation.execute(0.0, 5.0);
        assertEquals(0.0, result, 0.001);
        
        result = divisionOperation.execute(0.0, -5.0);
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    @DisplayName("Should throw exception when zero divided by zero")
    void shouldThrowExceptionForZeroDividedByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(0.0, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should handle large dividend with zero divisor")
    void shouldHandleLargeDividendWithZeroDivisor() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(Double.MAX_VALUE, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should handle negative dividend with zero divisor")
    void shouldHandleNegativeDividendWithZeroDivisor() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(-100.0, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should validate epsilon threshold for zero detection")
    void shouldValidateEpsilonThreshold() {
        // Test right at the boundary
        double epsilon = 1e-10;
        
        // Should throw for values smaller than epsilon
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> divisionOperation.execute(10.0, epsilon / 2));
        assertEquals("Division by zero is not allowed", exception.getMessage());
        
        // Should NOT throw for values larger than epsilon
        assertDoesNotThrow(() -> divisionOperation.execute(10.0, epsilon * 2));
    }
}
