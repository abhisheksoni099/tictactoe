package com.abhisheksoni.tictactoe.engine.core;

import com.abhisheksoni.tictactoe.command.parser.CommandParser;
import com.abhisheksoni.tictactoe.command.type.Command;
import com.abhisheksoni.tictactoe.command.type.PlayerMoveCommand;
import com.abhisheksoni.tictactoe.input.InputControl;
import com.abhisheksoni.tictactoe.model.game.*;
import com.abhisheksoni.tictactoe.model.player.Player;
import com.abhisheksoni.tictactoe.renderer.CommandLineRenderer;
import com.abhisheksoni.tictactoe.renderer.Renderer;

import java.util.ArrayDeque;

public class GameEngine {
    private final Renderer renderer;
    private final CommandParser commandParser;
    private final InputControl inputControl;
    private static GameEngine instance;

    private GameEngine() {
        this.renderer = CommandLineRenderer.getInstance();
        this.commandParser = CommandParser.getInstance();
        this.inputControl = InputControl.getInstance();
    }

    private synchronized static GameEngine initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new GameEngine();
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    public void play(Game game) {
        if (game.getGameState() == GameState.ACTIVE) {
            playActiveGame(game);
        } else {
            startGameTermination();
        }
    }

    private void playActiveGame(Game game) {
        GamePlayMetadata gamePlayMetadata = game.getGamePlayMetadata();
        if (checkIfPlayerHasWon(game)) {
            stopGame(game, GameState.WON);
            return;
        }
        if (checkIfGameIsDraw(game)) {
            stopGame(game, GameState.DRAW);
            return;
        }
        gamePlayMetadata.progressActivePlayer();
        renderer.render(game);
        Player activePlayer = gamePlayMetadata.getActivePlayer();
        String playerInput = activePlayer.play(game.getBoard());
        Command command = commandParser.parse(playerInput);
        if (!(command instanceof PlayerMoveCommand)) {
            command.execute();
            return;
        }
        command.execute();
    }

    private boolean checkIfPlayerHasWon(Game game) {
        if (game.getGamePlayMetadata().hasPlayerMoveRetracted()) {
            return false;
        }
        return game.getGameWinDetectionStrategy().check(game);
    }

    private boolean checkIfGameIsDraw(Game game) {
        for (Cell[] cellRow : game.getBoard().getCells()) {
            for (Cell cell : cellRow) {
                if (cell.getSymbol() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void stopGame(Game game, GameState gameState) {
        if (gameState == GameState.WON) {
            game.getGamePlayMetadata().setWinner(game.getGamePlayMetadata().getActivePlayer());
        }
        game.setGameState(gameState);
        renderer.renderGameStopped(game);
        game.play();
    }

    private void startGameTermination() {
        renderer.renderGameClosurePrompt();
        commandParser.parse(inputControl.string()).execute();
    }

    public void playMove(int cellRow, int cellColumn, Game game) {
        GamePlayMetadata gamePlayMetadata = game.getGamePlayMetadata();
        int size = game.getBoard().getCells().length;
        if (cellRow < 0 || cellRow >= size || cellColumn < 0 || cellColumn >= size) {
            gamePlayMetadata.setPlayerMoveRetracted();
            game.setWarning("The cell you choose to play on, does not exist");
            game.play();
            return;
        }
        Cell cell = game.getBoard().getCells()[cellRow][cellColumn];
        if (cell.getSymbol() != ' ') {
            gamePlayMetadata.setPlayerMoveRetracted();
            game.setWarning("Moves cannot be overridden");
            game.play();
            return;
        }
        cell.setSymbol(gamePlayMetadata.getActivePlayer().getSymbol());
        gamePlayMetadata.addActivePlayerMove(cellRow, cellColumn);
        game.play();
    }

    public void undo(Game game) {
        GamePlayMetadata gamePlayMetadata = game.getGamePlayMetadata();
        ArrayDeque<Move> moves = gamePlayMetadata.getHistory().getMoves();
        if (moves.isEmpty()) {
            gamePlayMetadata.setPlayerMoveRetracted();
            game.setWarning("There is no move to undo");
            game.play();
            return;
        }
        Move lastMove = moves.pop();
        clearMoveFromTheBoard(game.getBoard(), lastMove);
        gamePlayMetadata.getWinDetectionMetadata().removeMove(lastMove);
        gamePlayMetadata.retractActivePlayer();
        game.play();
    }

    private void clearMoveFromTheBoard(Board board, Move move) {
        int lastMoveCellRow = move.getCell().getRow();
        int lastMoveCellColumn = move.getCell().getColumn();
        board.getCells()[lastMoveCellRow][lastMoveCellColumn].setSymbol(' ');
    }

    public void end()  {
        renderer.renderEndGameMessage();
    }

    public void resetGame(Game game) {
        game.setGameState(GameState.ACTIVE);
        game.getGamePlayMetadata().reset();
        clearGameBoard(game.getBoard());
    }

    private void clearGameBoard(Board board) {
        for (Cell[] cellRow : board.getCells()) {
            for (Cell cell : cellRow) {
                cell.setSymbol(' ');
            }
        }
    }

    public void setWarning(String warningMessage, Game game) {
        if (warningMessage == null) {
            warningMessage = "Please enter a valid command";
        }
        game.getGamePlayMetadata().setWarningMessage(warningMessage);
    }

    public void resumeGame(Game game) {
        game.getGamePlayMetadata().setPlayerMoveRetracted();
        game.play();
    }
}
