package com.calculator.operation;

/**
 * Interface for arithmetic operations.
 * All calculator operations must implement this interface.
 */
public interface Operation {
    
    /**
     * Executes the operation on two operands.
     * 
     * @param leftOperand the left operand
     * @param rightOperand the right operand
     * @return the result of the operation
     * @throws ArithmeticException if the operation cannot be performed (e.g., division by zero)
     */
    double execute(double leftOperand, double rightOperand);
    
    /**
     * Returns the symbol representing this operation.
     * 
     * @return the operation symbol (e.g., "+", "-", "*", "/")
     */
    String getSymbol();
}
