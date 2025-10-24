package com.calculator.validation;

import com.calculator.util.InputValidator;
import com.calculator.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InputValidator class.
 * Tests validation of numeric inputs and edge cases.
 */
@DisplayName("Input Validation Tests")
class InputValidationTest {
    
    private InputValidator inputValidator;
    
    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
    }
    
    @Test
    @DisplayName("Should validate normal numbers successfully")
    void shouldValidateNormalNumbers() {
        assertDoesNotThrow(() -> inputValidator.validateNumber(5.0));
        assertDoesNotThrow(() -> inputValidator.validateNumber(-3.0));
        assertDoesNotThrow(() -> inputValidator.validateNumber(0.0));
        assertDoesNotThrow(() -> inputValidator.validateNumber(123.456));
    }
    
    @Test
    @DisplayName("Should throw exception for NaN")
    void shouldThrowExceptionForNaN() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateNumber(Double.NaN));
        assertEquals("Number cannot be NaN (Not a Number)", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for positive infinity")
    void shouldThrowExceptionForPositiveInfinity() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateNumber(Double.POSITIVE_INFINITY));
        assertEquals("Number cannot be infinite", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for negative infinity")
    void shouldThrowExceptionForNegativeInfinity() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateNumber(Double.NEGATIVE_INFINITY));
        assertEquals("Number cannot be infinite", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should validate supported operators successfully")
    void shouldValidateSupportedOperators() {
        assertDoesNotThrow(() -> inputValidator.validateOperator("+"));
        assertDoesNotThrow(() -> inputValidator.validateOperator("-"));
        assertDoesNotThrow(() -> inputValidator.validateOperator("*"));
        assertDoesNotThrow(() -> inputValidator.validateOperator("/"));
    }
    
    @Test
    @DisplayName("Should validate operators with whitespace")
    void shouldValidateOperatorsWithWhitespace() {
        assertDoesNotThrow(() -> inputValidator.validateOperator(" + "));
        assertDoesNotThrow(() -> inputValidator.validateOperator(" - "));
        assertDoesNotThrow(() -> inputValidator.validateOperator(" * "));
        assertDoesNotThrow(() -> inputValidator.validateOperator(" / "));
    }
    
    @Test
    @DisplayName("Should throw exception for null operator")
    void shouldThrowExceptionForNullOperator() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator(null));
        assertEquals("Operator cannot be null or empty", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for empty operator")
    void shouldThrowExceptionForEmptyOperator() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator(""));
        assertEquals("Operator cannot be null or empty", exception.getMessage());
        
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("   "));
        assertEquals("Operator cannot be null or empty", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for unsupported operators")
    void shouldThrowExceptionForUnsupportedOperators() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("^"));
        assertEquals("Unsupported operator: ^. Supported operators: +, -, *, /", exception.getMessage());
        
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("%"));
        assertEquals("Unsupported operator: %. Supported operators: +, -, *, /", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should validate division by non-zero successfully")
    void shouldValidateDivisionByNonZero() {
        assertDoesNotThrow(() -> inputValidator.validateDivision(5.0));
        assertDoesNotThrow(() -> inputValidator.validateDivision(-3.0));
        assertDoesNotThrow(() -> inputValidator.validateDivision(0.1));
    }
    
    @Test
    @DisplayName("Should throw exception for division by zero")
    void shouldThrowExceptionForDivisionByZero() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateDivision(0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw exception for division by very small number")
    void shouldThrowExceptionForDivisionByVerySmallNumber() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateDivision(1e-11));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should validate complete calculation successfully")
    void shouldValidateCompleteCalculation() {
        assertDoesNotThrow(() -> inputValidator.validateCalculation(5.0, "+", 3.0));
        assertDoesNotThrow(() -> inputValidator.validateCalculation(10.0, "-", 4.0));
        assertDoesNotThrow(() -> inputValidator.validateCalculation(6.0, "*", 7.0));
        assertDoesNotThrow(() -> inputValidator.validateCalculation(15.0, "/", 3.0));
    }
    
    @Test
    @DisplayName("Should throw exception for invalid calculation components")
    void shouldThrowExceptionForInvalidCalculationComponents() {
        // Invalid left operand
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateCalculation(Double.NaN, "+", 3.0));
        assertEquals("Number cannot be NaN (Not a Number)", exception.getMessage());
        
        // Invalid right operand
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateCalculation(5.0, "+", Double.POSITIVE_INFINITY));
        assertEquals("Number cannot be infinite", exception.getMessage());
        
        // Invalid operator
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateCalculation(5.0, "^", 3.0));
        assertEquals("Unsupported operator: ^. Supported operators: +, -, *, /", exception.getMessage());
        
        // Division by zero
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateCalculation(5.0, "/", 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}
