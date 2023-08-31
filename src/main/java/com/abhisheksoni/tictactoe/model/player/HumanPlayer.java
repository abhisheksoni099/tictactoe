package com.abhisheksoni.tictactoe.model.player;

import com.abhisheksoni.tictactoe.engine.playingstrategy.ManualPlayingStrategy;

public class HumanPlayer extends Player {
    public HumanPlayer(String id, char symbol) {
        super(id, symbol, PlayerType.HUMAN, new ManualPlayingStrategy());
    }
}
