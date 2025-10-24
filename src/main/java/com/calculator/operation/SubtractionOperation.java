package com.calculator.operation;

/**
 * Subtraction operation implementation.
 */
public class SubtractionOperation implements Operation {
    
    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }
    
    @Override
    public String getSymbol() {
        return "-";
    }
}
