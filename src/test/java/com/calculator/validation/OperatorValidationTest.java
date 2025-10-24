package com.calculator.validation;

import com.calculator.util.InputValidator;
import com.calculator.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Focused unit tests for operator validation functionality.
 * Tests specifically the operator validation logic in InputValidator.
 */
@DisplayName("Operator Validation Tests")
class OperatorValidationTest {
    
    private InputValidator inputValidator;
    
    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    @DisplayName("Should accept valid operators")
    void shouldAcceptValidOperators(String operator) {
        assertDoesNotThrow(() -> inputValidator.validateOperator(operator));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {" + ", " - ", " * ", " / "})
    @DisplayName("Should accept valid operators with whitespace")
    void shouldAcceptValidOperatorsWithWhitespace(String operator) {
        assertDoesNotThrow(() -> inputValidator.validateOperator(operator));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"^", "%", "**", "//", "++", "--", "=", "==", "!", "@", "#", "$", "&", "|", "~", "?", ":", ";", ",", ".", "<", ">", "[", "]", "{", "}", "(", ")"})
    @DisplayName("Should reject invalid operators")
    void shouldRejectInvalidOperators(String operator) {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator(operator));
        assertTrue(exception.getMessage().contains("Unsupported operator"));
        assertTrue(exception.getMessage().contains("Supported operators: +, -, *, /"));
    }
    
    @Test
    @DisplayName("Should reject null operator")
    void shouldRejectNullOperator() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator(null));
        assertEquals("Operator cannot be null or empty", exception.getMessage());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    @DisplayName("Should reject empty or whitespace-only operators")
    void shouldRejectEmptyOrWhitespaceOperators(String operator) {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator(operator));
        assertEquals("Operator cannot be null or empty", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should provide specific error message for each invalid operator")
    void shouldProvideSpecificErrorMessages() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("^"));
        assertEquals("Unsupported operator: ^. Supported operators: +, -, *, /", exception.getMessage());
        
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("%"));
        assertEquals("Unsupported operator: %. Supported operators: +, -, *, /", exception.getMessage());
        
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("**"));
        assertEquals("Unsupported operator: **. Supported operators: +, -, *, /", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should handle case sensitivity correctly")
    void shouldHandleCaseSensitivity() {
        // Operators should be case-sensitive (no uppercase versions)
        InvalidInputException exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("ADD"));
        assertTrue(exception.getMessage().contains("Unsupported operator: ADD"));
        
        exception = assertThrows(InvalidInputException.class, 
            () -> inputValidator.validateOperator("PLUS"));
        assertTrue(exception.getMessage().contains("Unsupported operator: PLUS"));
    }
}
