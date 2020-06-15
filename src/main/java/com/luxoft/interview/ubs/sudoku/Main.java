package com.luxoft.interview.ubs.sudoku;

public class Main {

    public static void main(String args[]) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expecting exactly one argument");
        }
        Validator validator = new Validator();
        ValidationResult result = validator.validate(args[0]);
        System.out.format("%d %s\n", result.getCode(), result.getMessage());
    }
}
