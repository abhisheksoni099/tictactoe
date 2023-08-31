package com.abhisheksoni.tictactoe.factory;

import com.abhisheksoni.tictactoe.exception.GameException;
import com.abhisheksoni.tictactoe.model.player.BotPlayer;
import com.abhisheksoni.tictactoe.model.player.HumanPlayer;
import com.abhisheksoni.tictactoe.model.player.Player;
import com.abhisheksoni.tictactoe.model.player.PlayerType;

public class PlayerFactory {
    private static PlayerFactory instance;

    private PlayerFactory() {
    }

    private synchronized static PlayerFactory initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new PlayerFactory();
    }

    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    public Player createPlayer(String playerId, char symbol) {
        PlayerType playerType = determinePlayerType(symbol);
        switch (playerType) {
            case HUMAN:
                return new HumanPlayer(playerId, symbol);
            case BOT:
                return new BotPlayer(playerId, symbol);
        }
        throw new GameException("Create player failed");
    }

    private PlayerType determinePlayerType(char symbol) {
        if (symbol == 'c' || symbol == 'C') {
            return PlayerType.BOT;
        }
        return PlayerType.HUMAN;
    }
}
