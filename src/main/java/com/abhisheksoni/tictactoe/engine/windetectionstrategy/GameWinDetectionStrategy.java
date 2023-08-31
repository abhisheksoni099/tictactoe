package com.abhisheksoni.tictactoe.engine.windetectionstrategy;

import com.abhisheksoni.tictactoe.model.game.Game;

public interface GameWinDetectionStrategy {
    boolean check(Game game);
}
