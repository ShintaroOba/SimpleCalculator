package com.calculator;

/**
 * Debug utility to check argument parsing.
 */
public class DebugArgs {
    public static void main(String[] args) {
        System.out.println("Number of arguments: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg[" + i + "]: '" + args[i] + "'");
        }
    }
}
