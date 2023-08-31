package com.abhisheksoni.tictactoe.model.game;

import com.abhisheksoni.tictactoe.model.player.Player;

public class GamePlayMetadata {
    private final History history;
    private final Game game;
    private final WinDetectionMetadata winDetectionMetadata;
    private int playerTurnIndex;
    private boolean hasPlayerMoveRetracted;
    private String warningMessage;
    private Player activePlayer;
    private Move lastMove;
    private Player winner;

    public GamePlayMetadata(Game game) {
        this.game = game;
        this.history = new History();
        this.winDetectionMetadata = new WinDetectionMetadata(game.getBoard().getCells().length);
        this.playerTurnIndex = -1;
        this.hasPlayerMoveRetracted = false;
    }

    public void reset() {
        this.warningMessage = null;
        this.history.getMoves().clear();
        this.playerTurnIndex = -1;
        this.hasPlayerMoveRetracted = false;
        this.winDetectionMetadata.reset();
        this.activePlayer = null;
        this.lastMove = null;
        this.winner = null;
    }

    public void addActivePlayerMove(int cellRow, int cellColumn) {
        Move move = new Move(activePlayer, new Cell(cellRow, cellColumn));
        history.getMoves().push(move);
        lastMove = move;
    }

    public void progressActivePlayer() {
        if (this.hasPlayerMoveRetracted) {
            this.hasPlayerMoveRetracted = false;
            return;
        }
        this.playerTurnIndex = this.playerTurnIndex + 1;
        this.activePlayer = game.getPlayers().get(playerTurnIndex % game.getPlayers().size());
    }

    public void retractActivePlayer() {
        setPlayerMoveRetracted();
        this.playerTurnIndex = this.playerTurnIndex - 1;
        this.activePlayer = game.getPlayers().get(playerTurnIndex % game.getPlayers().size());
    }

    public void setPlayerMoveRetracted() {
        this.hasPlayerMoveRetracted = true;
        this.lastMove = null;
    }

    public boolean hasPlayerMoveRetracted() {
        return hasPlayerMoveRetracted;
    }

    public History getHistory() {
        return history;
    }

    public WinDetectionMetadata getWinDetectionMetadata() {
        return winDetectionMetadata;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
