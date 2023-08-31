package com.abhisheksoni.tictactoe.engine.core;

import com.abhisheksoni.tictactoe.factory.PlayerFactory;
import com.abhisheksoni.tictactoe.model.game.Board;
import com.abhisheksoni.tictactoe.model.game.Game;
import com.abhisheksoni.tictactoe.model.game.GamePlayMetadata;
import com.abhisheksoni.tictactoe.model.game.GameState;
import com.abhisheksoni.tictactoe.model.player.Player;
import com.abhisheksoni.tictactoe.renderer.CommandLineRenderer;
import com.abhisheksoni.tictactoe.renderer.Renderer;
import com.abhisheksoni.tictactoe.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

public class GameInitializer {
    private static GameInitializer instance;
    private final Renderer renderer;
    private final GameCache gameCache;
    private final PlayerFactory playerFactory;

    private GameInitializer() {
        this.renderer = CommandLineRenderer.getInstance();
        this.gameCache = GameCache.getInstance();
        this.playerFactory = PlayerFactory.getInstance();
    }

    private synchronized static GameInitializer initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new GameInitializer();
    }

    public static GameInitializer getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    public Game initialize() {
        if (gameCache.isGameActive()) {
            gameCache.getCurrentGame().reinitialize();
            return gameCache.getCurrentGame();
        }
        Game game = new Game.Builder()
                .setBoard(inputBoard())
                .setPlayers(inputPlayers())
                .build();
        gameCache.setCurrentGame(game);
        return game;
    }

    public Game initialize(String[] arguments) {
        if (gameCache.isGameActive()) {
            gameCache.getCurrentGame().reinitialize();
            return gameCache.getCurrentGame();
        }
        Game game = new Game.Builder()
                .setBoard(parseBoard(arguments))
                .setPlayers(parsePlayers(arguments))
                .build();
        gameCache.setCurrentGame(game);
        return game;
    }

    public void reInitialize(Game game) {
        boolean result = renderer.renderYnPrompt("Would you like to create a new game (y/n)?: ");
        if (!result) {
            return;
        }
        game.setGameState(GameState.ACTIVE);
        game.setBoard(inputBoard());
        game.setPlayers(inputPlayers());
        game.setGamePlayMetadata(new GamePlayMetadata(game));
    }

    private Board inputBoard() {
        String boardSizeText = renderer.renderTextPrompt("Enter the board size: ");
        if (!NumberUtil.isNumeric(boardSizeText)) {
            renderer.render("\n\t#The board size must be numeric");
            return inputBoard();
        }
        int boardSize = Integer.parseInt(boardSizeText);
        if (boardSize < 1) {
            renderer.render("\n\t#The board size must be greater than 0");
            return inputBoard();
        }
        return new Board(boardSize);
    }

    private List<Player> inputPlayers() {
        String numberOfPlayersText = renderer.renderTextPrompt("Enter the number of players: ");
        if (!NumberUtil.isNumeric(numberOfPlayersText)) {
            renderer.render("\n\t#The number of players must be numeric");
            return inputPlayers();
        }
        int numberOfPlayers = Integer.parseInt(numberOfPlayersText);
        if (numberOfPlayers < 2) {
            renderer.render("\n\t#The number of players must be greater than 1");
            return inputPlayers();
        }
        List<Player> players = new ArrayList<>();
        for (int index = 1; index <= numberOfPlayers; index++) {
            String playerId = inputPlayerId(index);
            char playerSymbol = inputPlayerSymbol(index, players);
            players.add(playerFactory.createPlayer(playerId, playerSymbol));
        }
        return players;
    }

    private String inputPlayerId(int index) {
        String playerId = renderer.renderTextPrompt("Enter player " + index + "'s " + "id: ");
        if (playerId == null || playerId.trim().isEmpty()) {
            renderer.render("\n\t#The player id cannot be blank");
            return inputPlayerId(index);
        }
        return playerId;
    }

    private char inputPlayerSymbol(int index, List<Player> existingPlayers) {
        String playerSymbol = renderer.renderTextPrompt("Enter player " + index + "'s " + "symbol: ");
        if (playerSymbol == null || playerSymbol.trim().isEmpty()) {
            renderer.render("\n\t#The player symbol cannot be blank");
            return inputPlayerSymbol(index, existingPlayers);
        }
        if (playerSymbol.trim().length() > 1) {
            renderer.render("\n\t#The player symbol cannot have a length greater than 1");
            return inputPlayerSymbol(index, existingPlayers);
        }
        char finalPlayerSymbol = playerSymbol.trim().charAt(0);
        if (isSymbolTaken(finalPlayerSymbol, existingPlayers)) {
            renderer.render("\n\t#This symbol has already been taken");
            return inputPlayerSymbol(index, existingPlayers);
        }
        return finalPlayerSymbol;
    }

    private boolean isSymbolTaken(char playerSymbol, List<Player> existingPlayers) {
        for (Player existingPlayer : existingPlayers) {
            if (existingPlayer.getSymbol() == playerSymbol) {
                return true;
            }
        }
        return false;
    }

    private List<Player> parsePlayers(String[] arguments) {
        int argumentIndex = 1;
        int numberOfPlayers = Integer.parseInt(arguments[argumentIndex++]);
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < numberOfPlayers; index++) {
            players.add(playerFactory.createPlayer(arguments[argumentIndex++], arguments[argumentIndex++].charAt(0)));
        }
        return players;
    }

    private Board parseBoard(String[] arguments) {
        int boardSize = Integer.parseInt(arguments[arguments.length - 1]);
        return new Board(boardSize);
    }
}
