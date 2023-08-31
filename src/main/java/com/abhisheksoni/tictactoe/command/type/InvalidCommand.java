package com.abhisheksoni.tictactoe.command.type;

import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;

public class InvalidCommand extends BaseCommand {
    @Override
    public void execute() {
        game.resume();
    }

    @Override
    public CommandValidationResult validate() {
        return new CommandValidationResult(false);
    }
}
