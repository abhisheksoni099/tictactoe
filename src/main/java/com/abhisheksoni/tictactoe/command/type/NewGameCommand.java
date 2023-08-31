package com.abhisheksoni.tictactoe.command.type;

import com.abhisheksoni.tictactoe.command.model.CommandType;
import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;
import com.abhisheksoni.tictactoe.model.game.Game;
import com.abhisheksoni.tictactoe.util.NumberUtil;

public class NewGameCommand extends BaseCommand {
    public NewGameCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        Game game = gameInitializer.initialize(arguments);
        game.play();
    }

    @Override
    public CommandValidationResult validate() {
        if (arguments.length == 1 && CommandType.NEW_GAME.toString().equalsIgnoreCase(arguments[0])) {
            return super.validate();
        }
        try {
            if (!arguments[0].equals(CommandType.START_GAME_ARGUMENT.toString())) {
                return new CommandValidationResult(false, "Please use the '" + CommandType.START_GAME_ARGUMENT + "' command to start a new game");
            }
            if (arguments.length % 2 == 0) {
                return new CommandValidationResult(false);
            }
            String numberOfPlayersInput = arguments[1];
            String boardSizeInput = arguments[arguments.length - 1];
            if (NumberUtil.isNotNumeric(numberOfPlayersInput) || NumberUtil.isNotNumeric(boardSizeInput)) {
                return new CommandValidationResult(false);
            }
            int numberOfPlayers = Integer.parseInt(numberOfPlayersInput);
            if (numberOfPlayers < 2) {
                return new CommandValidationResult(false, "The number of players must be greater than 1");
            }
            if (Integer.parseInt(boardSizeInput) < 1) {
                return new CommandValidationResult(false, "The board size must be greater than 0");
            }
            for (int counter = 0, index = 3; counter < numberOfPlayers; counter++, index+=2) {
                if (arguments[index].length() != 1) {
                    return new CommandValidationResult(false, "The player symbol cannot have a length greater than 1");
                }
            }
        } catch (Exception e) {
            return new CommandValidationResult(false);
        }
        return new CommandValidationResult(true);
    }
}
