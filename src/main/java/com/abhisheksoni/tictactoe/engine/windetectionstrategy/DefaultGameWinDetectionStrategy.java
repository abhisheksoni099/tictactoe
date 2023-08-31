package com.abhisheksoni.tictactoe.engine.windetectionstrategy;

import com.abhisheksoni.tictactoe.model.game.*;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;

public class DefaultGameWinDetectionStrategy implements GameWinDetectionStrategy {
    @Override
    public boolean check(Game game) {
        GamePlayMetadata gamePlayMetadata = game.getGamePlayMetadata();
        ArrayDeque<Move> moves = gamePlayMetadata.getHistory().getMoves();
        if (moves.isEmpty()) {
            return false;
        }
        Move lastMove = gamePlayMetadata.getLastMove();
        if (lastMove == null) {
            return false;
        }

        WinDetectionMetadata winDetectionMetadata = gamePlayMetadata.getWinDetectionMetadata();
        List<Map<Character, Integer>> columnSymbolCounters = winDetectionMetadata.getColumnSymbolCounters();
        List<Map<Character, Integer>> rowSymbolCounters = winDetectionMetadata.getRowSymbolCounters();
        Map<Character, Integer> lrDiagonalSymbolCounter = winDetectionMetadata.getLrDiagonalSymbolCounter();
        Map<Character, Integer> rlDiagonalSymbolCounter = winDetectionMetadata.getRlDiagonalSymbolCounter();

        Cell cell = lastMove.getCell();
        char symbol = lastMove.getPlayer().getSymbol();
        int size = game.getBoard().getCells().length;

        int rowSymbolCount = updateSymbolCounter(rowSymbolCounters, cell.getRow(), symbol);
        if (rowSymbolCount == size) {
            return true;
        }

        int columnSymbolCount = updateSymbolCounter(columnSymbolCounters, cell.getColumn(), symbol);
        if (columnSymbolCount == size) {
            return true;
        }

        if (doesCellLieOnTheLrDiagonal(cell)) {
            int lrDiagonalSymbolCount = updateSymbolCounter(lrDiagonalSymbolCounter, symbol);
            if (lrDiagonalSymbolCount == size) {
                return true;
            }
        }

        if (doesCellLieOnTheRlDiagonal(cell, size)) {
            int rlDiagonalSymbolCount = updateSymbolCounter(rlDiagonalSymbolCounter, symbol);
            if (rlDiagonalSymbolCount == size) {
                return true;
            }
        }
        return false;
    }

    int updateSymbolCounter(List<Map<Character, Integer>> symbolCounters, int symbolIndex, char symbol) {
        Map<Character, Integer> symbolCounter = symbolCounters.get(symbolIndex);
        return updateSymbolCounter(symbolCounter, symbol);
    }

    int updateSymbolCounter(Map<Character, Integer> symbolCounter, char symbol) {
        symbolCounter.putIfAbsent(symbol, 0);
        symbolCounter.put(symbol, symbolCounter.get(symbol) + 1);
        return symbolCounter.get(symbol);
    }

    private boolean doesCellLieOnTheLrDiagonal(Cell cell) {
        return cell.getRow() == cell.getColumn();
    }

    private boolean doesCellLieOnTheRlDiagonal(Cell cell, int size) {
        return (cell.getRow() + cell.getColumn() == size - 1);
    }
}
