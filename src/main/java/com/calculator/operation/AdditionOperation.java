package com.calculator.operation;

/**
 * Addition operation implementation.
 */
public class AdditionOperation implements Operation {
    
    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }
    
    @Override
    public String getSymbol() {
        return "+";
    }
}
