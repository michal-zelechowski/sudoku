package com.luxoft.interview.ubs.sudoku;

public class ValidationResult {
    private final Code code;
    private final String message;

    private ValidationResult(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code.ordinal();
    }

    public String getMessage() {
        return this.message;
    }

    public static ValidationResult correct() {
        return new ValidationResult(Code.OK, "");
    }

    public static ValidationResult incorrect(Code code, String message) {
        return new ValidationResult(code, message);
    }

    public enum Code {
        OK,
        READ_ERROR,
        INCORRECT_SOLUTION,
        MALFORMED_INPUT,
        UNEXPECTED_ERROR
    }
}
