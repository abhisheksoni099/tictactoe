package com.abhisheksoni.tictactoe.model.game;

public class Board {
    private final Cell[][] cells;

    public Board(int size) {
        this.cells = initializeCells(size);
    }

    private Cell[][] initializeCells(int size) {
        Cell[][] cells = new Cell[size][size];
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int colIndex = 0; colIndex < size; colIndex++) {
                cells[rowIndex][colIndex] = new Cell(rowIndex, colIndex);
            }
        }
        return cells;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
