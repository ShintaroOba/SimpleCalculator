package com.calculator;

import com.calculator.util.ArgumentParser;
import com.calculator.util.ErrorHandler;
import com.calculator.util.HelpDisplay;
import com.calculator.util.VersionDisplay;
import com.calculator.util.InputValidator;
import com.calculator.exception.InvalidArgumentException;
import com.calculator.exception.InvalidInputException;

/**
 * Main application class for the CLI Calculator.
 * Handles command line arguments, coordinates operations, and manages output.
 */
public class CalculatorApp {
    
    private final Calculator calculator;
    private final ArgumentParser argumentParser;
    private final ErrorHandler errorHandler;
    private final HelpDisplay helpDisplay;
    private final VersionDisplay versionDisplay;
    private final InputValidator inputValidator;
    
    /**
     * Constructs a CalculatorApp with default dependencies.
     */
    public CalculatorApp() {
        this.calculator = new Calculator();
        this.argumentParser = new ArgumentParser();
        this.errorHandler = new ErrorHandler();
        this.helpDisplay = new HelpDisplay();
        this.versionDisplay = new VersionDisplay();
        this.inputValidator = new InputValidator();
    }
    
    /**
     * Main entry point for the calculator application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        int exitCode = app.run(args);
        System.exit(exitCode);
    }
    
    /**
     * Runs the calculator application with the given arguments.
     * 
     * @param args command line arguments
     * @return exit code (0 for success, 1 for error)
     */
    public int run(String[] args) {
        try {
            ArgumentParser.ParsedArguments parsedArgs = argumentParser.parse(args);
            
            if (parsedArgs.isHelpRequest()) {
                System.out.println(helpDisplay.getHelpMessage());
                return 0;
            }
            
            if (parsedArgs.isVersionRequest()) {
                System.out.println(versionDisplay.getVersionMessage());
                return 0;
            }
            
            // Validate input before calculation
            inputValidator.validateCalculation(
                parsedArgs.getLeftOperand(),
                parsedArgs.getOperator(),
                parsedArgs.getRightOperand()
            );
            
            // Perform calculation
            double result = calculator.calculate(
                parsedArgs.getLeftOperand(),
                parsedArgs.getOperator(),
                parsedArgs.getRightOperand()
            );
            
            // Output result
            System.out.println(formatResult(result));
            return 0;
            
        } catch (InvalidArgumentException | InvalidInputException e) {
            System.err.println(errorHandler.handleError(e));
            return 1;
        } catch (Exception e) {
            System.err.println(errorHandler.handleError(e));
            return 1;
        }
    }
    
    /**
     * Formats the calculation result for output.
     * 
     * @param result the calculation result
     * @return formatted result string
     */
    private String formatResult(double result) {
        // Check if the result is a whole number
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.valueOf((long) result);
        } else {
            return String.valueOf(result);
        }
    }
}
