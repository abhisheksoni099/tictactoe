package com.abhisheksoni.tictactoe.command.type;

public class ExitGameCommand extends BaseCommand {
    @Override
    public void execute() {
        boolean result = renderer.renderYnPrompt("Do you want to exit the game (y/n)?: ");
        if (result) {
            game.end();
        } else {
            game.play();
        }
    }
}
