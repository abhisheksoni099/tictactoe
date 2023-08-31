package com.abhisheksoni.tictactoe.command.type;

public class ResetGameCommand extends BaseCommand {
    public ResetGameCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        boolean result = renderer.renderYnPrompt("Do you want to reset the game (y/n)?: ");
        if (result) {
            game.reset();
        }
        game.play();
    }
}
