package com.calculator.operation;

/**
 * Multiplication operation implementation.
 */
public class MultiplicationOperation implements Operation {
    
    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }
    
    @Override
    public String getSymbol() {
        return "*";
    }
}
