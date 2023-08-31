package com.abhisheksoni.tictactoe.engine.core;

import com.abhisheksoni.tictactoe.model.game.Game;

public class GameCache {
    private Game currentGame;

    private static GameCache instance;

    private GameCache() {
    }

    public boolean isGameActive() {
        return currentGame != null;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    private synchronized static GameCache initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new GameCache();
    }

    public static GameCache getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }
}
