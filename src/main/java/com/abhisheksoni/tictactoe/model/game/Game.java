package com.abhisheksoni.tictactoe.model.game;

import com.abhisheksoni.tictactoe.engine.core.GameEngine;
import com.abhisheksoni.tictactoe.engine.core.GameInitializer;
import com.abhisheksoni.tictactoe.engine.windetectionstrategy.DefaultGameWinDetectionStrategy;
import com.abhisheksoni.tictactoe.engine.windetectionstrategy.GameWinDetectionStrategy;
import com.abhisheksoni.tictactoe.model.player.Player;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private GamePlayMetadata gamePlayMetadata;
    private final GameEngine gameEngine;
    private final GameWinDetectionStrategy gameWinDetectionStrategy;

    private Game(Builder builder) {
        this.board = builder.board;
        this.players = builder.players;
        this.gameState = GameState.ACTIVE;
        this.gamePlayMetadata = new GamePlayMetadata(this);
        this.gameEngine = GameEngine.getInstance();
        this.gameWinDetectionStrategy = new DefaultGameWinDetectionStrategy();
    }

    public void play() {
        gameEngine.play(this);
    }

    public void resume() {
        gameEngine.resumeGame(this);
    }

    public void playMove(int cellRow, int cellColumn) {
        gameEngine.playMove(cellRow, cellColumn, this);
    }

    public void undo() {
        gameEngine.undo(this);
    }

    public void end() {
        gameEngine.end();
    }

    public void reinitialize() {
        GameInitializer.getInstance().reInitialize(this);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GamePlayMetadata getGamePlayMetadata() {
        return gamePlayMetadata;
    }

    public void setGamePlayMetadata(GamePlayMetadata gamePlayMetadata) {
        this.gamePlayMetadata = gamePlayMetadata;
    }

    public void reset() {
        gameEngine.resetGame(this);
    }

    public void setWarning(String warningMessage) {
        gameEngine.setWarning(warningMessage, this);
    }

    public GameWinDetectionStrategy getGameWinDetectionStrategy() {
        return gameWinDetectionStrategy;
    }

    public static class Builder {
        private Board board;
        private List<Player> players;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
