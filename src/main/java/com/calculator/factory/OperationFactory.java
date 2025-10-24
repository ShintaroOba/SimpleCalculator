package com.calculator.factory;

import com.calculator.operation.Operation;
import com.calculator.operation.AdditionOperation;
import com.calculator.operation.SubtractionOperation;
import com.calculator.operation.MultiplicationOperation;
import com.calculator.operation.DivisionOperation;

/**
 * Factory class for creating operation instances.
 * Provides a centralized way to create operations based on operator symbols.
 */
public class OperationFactory {
    
    /**
     * Creates an operation instance based on the operator symbol.
     * 
     * @param operator the operator symbol (+, -, *, /)
     * @return the corresponding operation instance
     * @throws IllegalArgumentException if the operator is not supported
     */
    public Operation createOperation(String operator) {
        if (operator == null) {
            throw new IllegalArgumentException("Operator cannot be null");
        }
        
        switch (operator.trim()) {
            case "+":
                return new AdditionOperation();
            case "-":
                return new SubtractionOperation();
            case "*":
                return new MultiplicationOperation();
            case "/":
                return new DivisionOperation();
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
