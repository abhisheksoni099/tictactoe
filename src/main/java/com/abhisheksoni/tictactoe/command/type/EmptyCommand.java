package com.abhisheksoni.tictactoe.command.type;

import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;
import com.abhisheksoni.tictactoe.model.game.Game;

public class EmptyCommand extends BaseCommand {
    @Override
    public void execute() {
        if (gameCache.isGameActive()) {
            Game game = gameCache.getCurrentGame();
            game.play();
        } else {
            game = gameInitializer.initialize();
            game.play();
        }
    }

    @Override
    public CommandValidationResult validate() {
        return new CommandValidationResult(false, "Please enter a command");
    }
}
