package com.abhisheksoni.tictactoe.engine.playingstrategy;

import com.abhisheksoni.tictactoe.exception.GameException;
import com.abhisheksoni.tictactoe.model.game.Board;
import com.abhisheksoni.tictactoe.model.game.Cell;

public class BotPlayingStrategy implements GamePlayingStrategy {
    @Override
    public String play(Board board, char symbol) {
        for (Cell[] rowCells : board.getCells()) {
            for (Cell cell : rowCells) {
                if (cell.getSymbol() != symbol) {
                    continue;
                }
                Cell adjacentCell = findAdjacentCellFor(cell, board);
                if (adjacentCell != null) {
                    return buildMove(adjacentCell);
                }
            }
        }
        return buildMove(findAnEmptyCell(board));
    }

    private Cell findAnEmptyCell(Board board) {
        for (Cell[] rowCells : board.getCells()) {
            for (Cell cell : rowCells) {
                if (cell.getSymbol() == ' ') {
                    return cell;
                }
            }
        }
        throw new GameException("Bot could not find an empty cell");
    }

    private Cell findAdjacentCellFor(Cell cell, Board board) {
        int size = board.getCells().length;
        if (cell.getColumn() > 0) {
            Cell leftCell = board.getCells()[cell.getRow()][cell.getColumn() - 1];
            if (leftCell.getSymbol() == ' ') {
                return leftCell;
            }
        }
        if (cell.getColumn() < size - 1) {
            Cell rightCell = board.getCells()[cell.getRow()][cell.getColumn() + 1];
            if (rightCell.getSymbol() == ' ') {
                return rightCell;
            }
        }
        if (cell.getRow() > 0) {
            Cell topCell = board.getCells()[cell.getRow() - 1][cell.getColumn()];
            if (topCell.getSymbol() == ' ') {
                return topCell;
            }
        }
        if (cell.getRow() > size - 1) {
            Cell bottomCell = board.getCells()[cell.getRow() + 1][cell.getColumn()];
            if (bottomCell.getSymbol() == ' ') {
                return bottomCell;
            }
        }
        return null;
    }

    private String buildMove(Cell cell) {
        return cell.getRow() + " " + cell.getColumn();
    }
}
