package com.abhisheksoni.tictactoe.model.game;

import java.util.ArrayDeque;

public class History {
    private final ArrayDeque<Move> moves;

    public History() {
        this.moves = new ArrayDeque<>();
    }

    public ArrayDeque<Move> getMoves() {
        return moves;
    }
}
