package com.abhisheksoni.tictactoe.command.type;

import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;
import com.abhisheksoni.tictactoe.util.NumberUtil;

public class PlayerMoveCommand extends BaseCommand {
    private int cellRow;
    private int cellColumn;

    public PlayerMoveCommand(String[] arguments) {
        super(arguments);
        initializeCellRowAndColumn();
    }

    private void initializeCellRowAndColumn() {
        this.cellRow = Integer.parseInt(arguments[0]);
        this.cellColumn = Integer.parseInt(arguments[1]);
    }

    public void execute() {
        game.playMove(cellRow, cellColumn);
    }

    @Override
    public CommandValidationResult validate() {
        if (NumberUtil.isNumeric(arguments[0]) && NumberUtil.isNumeric(arguments[1])) {
            return super.validate();
        }
        return new CommandValidationResult(false);
    }
}
