package com.abhisheksoni.tictactoe.engine.playingstrategy;

import com.abhisheksoni.tictactoe.model.game.Board;

public interface GamePlayingStrategy {
    String play(Board board, char symbol);
}
