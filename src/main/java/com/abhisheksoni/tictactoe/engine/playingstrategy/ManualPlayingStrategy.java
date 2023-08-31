package com.abhisheksoni.tictactoe.engine.playingstrategy;

import com.abhisheksoni.tictactoe.input.InputControl;
import com.abhisheksoni.tictactoe.model.game.Board;

public class ManualPlayingStrategy implements GamePlayingStrategy {
    @Override
    public String play(Board board, char symbol) {
        return InputControl.getInstance().string();
    }
}
