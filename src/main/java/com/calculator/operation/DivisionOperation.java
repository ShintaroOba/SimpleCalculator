package com.calculator.operation;

/**
 * Division operation implementation.
 */
public class DivisionOperation implements Operation {
    
    @Override
    public double execute(double leftOperand, double rightOperand) {
        if (Math.abs(rightOperand) < 1e-10) {  // Use epsilon for double comparison
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return leftOperand / rightOperand;
    }
    
    @Override
    public String getSymbol() {
        return "/";
    }
}
