package com.abhisheksoni.tictactoe.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinDetectionMetadata {
    private final int size;
    private final List<Map<Character, Integer>> rowSymbolCounters;
    private final List<Map<Character, Integer>> columnSymbolCounters;
    private final Map<Character, Integer> lrDiagonalSymbolCounter;
    private final Map<Character, Integer> rlDiagonalSymbolCounter;

    public WinDetectionMetadata(int size) {
        this.size = size;
        rowSymbolCounters = new ArrayList<>(size);
        columnSymbolCounters = new ArrayList<>(size);
        lrDiagonalSymbolCounter = new HashMap<>();
        rlDiagonalSymbolCounter = new HashMap<>();
        addEmptySymbolCounters();
    }

    public void reset() {
        rowSymbolCounters.clear();
        columnSymbolCounters.clear();
        lrDiagonalSymbolCounter.clear();
        rlDiagonalSymbolCounter.clear();
        addEmptySymbolCounters();
    }

    private void addEmptySymbolCounters() {
        for (int index = 0; index < size; index++) {
            this.rowSymbolCounters.add(new HashMap<>());
            this.columnSymbolCounters.add(new HashMap<>());
        }
    }

    public void removeMove(Move move) {
        char symbol = move.getPlayer().getSymbol();
        Map<Character, Integer> rowSymbolCounter = rowSymbolCounters.get(move.getCell().getRow());
        rowSymbolCounter.put(symbol, rowSymbolCounter.get(symbol) - 1);
        Map<Character, Integer> columnSymbolCounter = columnSymbolCounters.get(move.getCell().getColumn());
        columnSymbolCounter.put(symbol, columnSymbolCounter.get(symbol) - 1);
        if (doesCellLieOnTheLrDiagonal(move.getCell())) {
            lrDiagonalSymbolCounter.put(symbol, lrDiagonalSymbolCounter.get(symbol) - 1);
        }
        if (doesCellLieOnTheRlDiagonal(move.getCell())) {
            rlDiagonalSymbolCounter.put(symbol, rlDiagonalSymbolCounter.get(symbol) - 1);
        }
    }

    private boolean doesCellLieOnTheLrDiagonal(Cell cell) {
        return cell.getRow() == cell.getColumn();
    }

    private boolean doesCellLieOnTheRlDiagonal(Cell cell) {
        return (cell.getRow() + cell.getColumn() == size - 1);
    }

    public List<Map<Character, Integer>> getRowSymbolCounters() {
        return rowSymbolCounters;
    }

    public List<Map<Character, Integer>> getColumnSymbolCounters() {
        return columnSymbolCounters;
    }

    public Map<Character, Integer> getLrDiagonalSymbolCounter() {
        return lrDiagonalSymbolCounter;
    }

    public Map<Character, Integer> getRlDiagonalSymbolCounter() {
        return rlDiagonalSymbolCounter;
    }
}
