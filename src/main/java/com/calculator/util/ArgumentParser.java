package com.calculator.util;

import com.calculator.exception.InvalidArgumentException;

/**
 * Utility class for parsing command line arguments.
 * Handles parsing of calculator commands in the format: "number operator number"
 * Also handles special flags like --help and --version.
 */
public class ArgumentParser {
    
    /**
     * Represents parsed calculator arguments.
     */
    public static class ParsedArguments {
        private final double leftOperand;
        private final String operator;
        private final double rightOperand;
        private final boolean isHelpRequest;
        private final boolean isVersionRequest;
        
        public ParsedArguments(double leftOperand, String operator, double rightOperand) {
            this.leftOperand = leftOperand;
            this.operator = operator;
            this.rightOperand = rightOperand;
            this.isHelpRequest = false;
            this.isVersionRequest = false;
        }
        
        public ParsedArguments(boolean isHelpRequest, boolean isVersionRequest) {
            this.leftOperand = 0;
            this.operator = null;
            this.rightOperand = 0;
            this.isHelpRequest = isHelpRequest;
            this.isVersionRequest = isVersionRequest;
        }
        
        public double getLeftOperand() { return leftOperand; }
        public String getOperator() { return operator; }
        public double getRightOperand() { return rightOperand; }
        public boolean isHelpRequest() { return isHelpRequest; }
        public boolean isVersionRequest() { return isVersionRequest; }
    }
    
    /**
     * Parses command line arguments into a structured format.
     * 
     * @param args the command line arguments
     * @return parsed arguments object
     * @throws InvalidArgumentException if arguments are invalid
     */
    public ParsedArguments parse(String[] args) throws InvalidArgumentException {
        if (args == null || args.length == 0) {
            throw new InvalidArgumentException("No arguments provided. Use --help for usage information.");
        }
        
        // Handle special flags
        if (args.length == 1) {
            if ("--help".equals(args[0]) || "-h".equals(args[0])) {
                return new ParsedArguments(true, false);
            }
            if ("--version".equals(args[0]) || "-v".equals(args[0])) {
                return new ParsedArguments(false, true);
            }
        }
        
        // Handle calculation arguments
        if (args.length != 3) {
            throw new InvalidArgumentException("Invalid number of arguments. Expected format: <number> <operator> <number>");
        }
        
        try {
            double leftOperand = Double.parseDouble(args[0]);
            String operator = args[1];
            double rightOperand = Double.parseDouble(args[2]);
            
            return new ParsedArguments(leftOperand, operator, rightOperand);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid number format: " + e.getMessage());
        }
    }
}
