package com.calculator;

import com.calculator.operation.Operation;
import com.calculator.factory.OperationFactory;

/**
 * Core Calculator class that performs arithmetic operations.
 * This class serves as the main computation engine for the CLI calculator.
 */
public class Calculator {
    
    private final OperationFactory operationFactory;
    
    /**
     * Constructs a Calculator with the default operation factory.
     */
    public Calculator() {
        this.operationFactory = new OperationFactory();
    }
    
    /**
     * Constructs a Calculator with a custom operation factory.
     * 
     * @param operationFactory the factory to create operations
     */
    public Calculator(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }
    
    /**
     * Performs a calculation with two operands and an operator.
     * 
     * @param leftOperand the left operand
     * @param operator the operator symbol (+, -, *, /)
     * @param rightOperand the right operand
     * @return the result of the calculation
     * @throws IllegalArgumentException if the operator is not supported
     * @throws ArithmeticException if division by zero occurs
     */
    public double calculate(double leftOperand, String operator, double rightOperand) {
        Operation operation = operationFactory.createOperation(operator);
        return operation.execute(leftOperand, rightOperand);
    }
}
