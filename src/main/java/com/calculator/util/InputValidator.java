package com.calculator.util;

import com.calculator.exception.InvalidInputException;

/**
 * Utility class for validating calculator inputs.
 * Provides validation for numeric values and operators.
 */
public class InputValidator {
    
    private static final String[] VALID_OPERATORS = {"+", "-", "*", "/"};
    
    /**
     * Validates that a number is valid for calculations.
     * 
     * @param number the number to validate
     * @throws InvalidInputException if the number is invalid
     */
    public void validateNumber(double number) throws InvalidInputException {
        if (Double.isNaN(number)) {
            throw new InvalidInputException("Number cannot be NaN (Not a Number)");
        }
        if (Double.isInfinite(number)) {
            throw new InvalidInputException("Number cannot be infinite");
        }
    }
    
    /**
     * Validates that an operator is supported.
     * 
     * @param operator the operator to validate
     * @throws InvalidInputException if the operator is not supported
     */
    public void validateOperator(String operator) throws InvalidInputException {
        if (operator == null || operator.trim().isEmpty()) {
            throw new InvalidInputException("Operator cannot be null or empty");
        }
        
        for (String validOp : VALID_OPERATORS) {
            if (validOp.equals(operator.trim())) {
                return;
            }
        }
        
        throw new InvalidInputException("Unsupported operator: " + operator + ". Supported operators: +, -, *, /");
    }
    
    /**
     * Validates division operation to prevent division by zero.
     * 
     * @param divisor the divisor value
     * @throws InvalidInputException if divisor is zero
     */
    public void validateDivision(double divisor) throws InvalidInputException {
        if (Math.abs(divisor) < 1e-10) {  // Use epsilon for double comparison
            throw new InvalidInputException("Division by zero is not allowed");
        }
    }
    
    /**
     * Validates all components of a calculation.
     * 
     * @param leftOperand the left operand
     * @param operator the operator
     * @param rightOperand the right operand
     * @throws InvalidInputException if any component is invalid
     */
    public void validateCalculation(double leftOperand, String operator, double rightOperand) 
            throws InvalidInputException {
        validateNumber(leftOperand);
        validateNumber(rightOperand);
        validateOperator(operator);
        
        if ("/".equals(operator.trim())) {
            validateDivision(rightOperand);
        }
    }
}
