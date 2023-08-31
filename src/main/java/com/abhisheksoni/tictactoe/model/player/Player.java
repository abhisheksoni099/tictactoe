package com.abhisheksoni.tictactoe.model.player;

import com.abhisheksoni.tictactoe.engine.playingstrategy.GamePlayingStrategy;
import com.abhisheksoni.tictactoe.model.game.Board;

public abstract class Player {
    protected String id;
    protected char symbol;
    protected PlayerType playerType;
    protected GamePlayingStrategy gamePlayingStrategy;

    public Player(String id, char symbol, PlayerType playerType, GamePlayingStrategy gamePlayingStrategy) {
        this.id = id;
        this.symbol = symbol;
        this.playerType = playerType;
        this.gamePlayingStrategy = gamePlayingStrategy;
    }

    public String play(Board board) {
        return gamePlayingStrategy.play(board, symbol);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(id);
        stringBuilder.append(" (");
        stringBuilder.append(symbol);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
