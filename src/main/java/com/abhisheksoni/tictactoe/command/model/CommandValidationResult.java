package com.abhisheksoni.tictactoe.command.model;

public class CommandValidationResult {
    private final boolean isSuccess;
    private String message;

    public CommandValidationResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public CommandValidationResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
