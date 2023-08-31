package com.abhisheksoni.tictactoe.renderer;

import com.abhisheksoni.tictactoe.model.game.Game;

public interface Renderer {
    void render(String message);
    void renderWarning(String message);
    String renderTextPrompt(String message);
    boolean renderYnPrompt(String message);
    void render(Game game);
    void renderGameClosurePrompt();
    void renderGameStopped(Game game);
    void renderEndGameMessage();
}
