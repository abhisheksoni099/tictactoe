package com.abhisheksoni.tictactoe.model.player;

import com.abhisheksoni.tictactoe.engine.playingstrategy.BotPlayingStrategy;

public class BotPlayer extends Player {
    public BotPlayer(String id, char symbol) {
        super(id, symbol, PlayerType.BOT, new BotPlayingStrategy());
    }
}
