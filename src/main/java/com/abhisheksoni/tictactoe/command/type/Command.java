package com.abhisheksoni.tictactoe.command.type;

import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;

public interface Command {
    void execute();
    CommandValidationResult validate();
}
