package com.abhisheksoni.tictactoe.command.parser;

import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;
import com.abhisheksoni.tictactoe.command.type.*;
import com.abhisheksoni.tictactoe.engine.core.GameCache;

public class CommandParserHelper {
    public Command parse(String[] arguments) {
        Command command;
        switch (arguments.length) {
            case 0:
                command = new EmptyCommand();
                break;
            case 1:
                command = parseSingleWordCommand(arguments);
                break;
            case 2:
                command = parseDoubleWordCommand(arguments);
                break;
            default:
                command = new NewGameCommand(arguments);
        }
        validateCommand(command);
        return command;
    }

    private void validateCommand(Command command) {
        CommandValidationResult commandValidationResult = command.validate();
        if (commandValidationResult.isSuccess()) {
            return;
        }
        GameCache.getInstance().getCurrentGame().setWarning(commandValidationResult.getMessage());
    }

    private Command parseSingleWordCommand(String[] arguments) {
        String argument = arguments[0];
        argument = argument.toLowerCase();
        switch (argument) {
            case "u":
                return new UndoMoveCommand(arguments);
            case "r":
                return new ResetGameCommand(arguments);
            case "n":
                return new NewGameCommand(arguments);
            case "e":
                return new ExitGameCommand();
            default:
                return new InvalidCommand();
        }
    }

    private Command parseDoubleWordCommand(String[] arguments) {
        return new PlayerMoveCommand(arguments);
    }
}
