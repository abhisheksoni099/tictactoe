package com.abhisheksoni.tictactoe;

import com.abhisheksoni.tictactoe.engine.core.GameCache;
import com.abhisheksoni.tictactoe.engine.core.GameInitializer;
import com.abhisheksoni.tictactoe.model.game.Game;
import com.abhisheksoni.tictactoe.model.game.GameState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {
    @Test
    void checkGameWon() {
        String input = "StartGame 2 u1 x u2 0 3";
        Game game = GameInitializer.getInstance().initialize(buildArguments(input));
        List<String> preloadedInputs = new ArrayList<>();
        preloadedInputs.add("0 0");
        preloadedInputs.add("0 1");
        preloadedInputs.add("1 1");
        preloadedInputs.add("0 2");
        preloadedInputs.add("2 2");
        preloadedInputs.add("e");
        preloadedInputs.add("y");
        GameCache.getInstance().getPreloadedInputs().addAll(preloadedInputs);
        game.play();

        assertEquals(game.getGameState(), GameState.WON);
        assertEquals(game.getGamePlayMetadata().getWinner().getId(), "u1");
    }

    @Test
    void checkGameDraw() {
        String input = "StartGame 2 u1 x u2 0 3";
        GameCache.getInstance().clear();
        Game game = GameInitializer.getInstance().initialize(buildArguments(input));
        List<String> preloadedInputs = new ArrayList<>();
        preloadedInputs.add("0 0");
        preloadedInputs.add("0 1");
        preloadedInputs.add("1 1");
        preloadedInputs.add("0 2");
        preloadedInputs.add("2 1");
        preloadedInputs.add("1 0");
        preloadedInputs.add("2 0");
        preloadedInputs.add("2 2");
        preloadedInputs.add("1 2");
        preloadedInputs.add("e");
        preloadedInputs.add("y");
        GameCache.getInstance().getPreloadedInputs().addAll(preloadedInputs);
        game.play();

        assertEquals(game.getGameState(), GameState.DRAW);
    }

    private String[] buildArguments(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input);
        String[] arguments = new String[stringTokenizer.countTokens()];
        int index = 0;
        while (stringTokenizer.hasMoreTokens()) {
            arguments[index++] = stringTokenizer.nextToken();
        }
        return arguments;
    }
}
