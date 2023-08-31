package com.abhisheksoni.tictactoe.engine.core;

import com.abhisheksoni.tictactoe.model.game.Game;

import java.util.LinkedList;
import java.util.Queue;

public class GameCache {
    private Game currentGame;

    private Queue<String> preloadedInputs;

    private static GameCache instance;

    private GameCache() {
        preloadedInputs = new LinkedList<>();
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

    public Queue<String> getPreloadedInputs() {
        return preloadedInputs;
    }

    public void clear() {
        this.currentGame = null;
        this.getPreloadedInputs().clear();
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
