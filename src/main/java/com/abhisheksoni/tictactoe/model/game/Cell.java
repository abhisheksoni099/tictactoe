package com.abhisheksoni.tictactoe.model.game;

public class Cell {
    private final int row;
    private final int column;
    private char symbol;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.symbol = ' ';
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
