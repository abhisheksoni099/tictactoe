package com.abhisheksoni.tictactoe.command.type;

public class UndoMoveCommand extends BaseCommand {
    public UndoMoveCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        game.undo();
    }
}
