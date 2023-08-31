package com.abhisheksoni.tictactoe.command.model;

public enum CommandType {
    EMPTY,
    EXIT_GAME("e"),
    INVALID,
    START_GAME_ARGUMENT("StartGame"),
    NEW_GAME("n"),
    PLAYER_MOVE,
    RESET_GAME("r"),
    UNDO_MOVE("u");

    final String name;

    CommandType() {
        this.name = "";
    }

    CommandType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
