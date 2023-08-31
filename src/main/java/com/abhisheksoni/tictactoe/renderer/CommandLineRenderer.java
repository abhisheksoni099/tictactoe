package com.abhisheksoni.tictactoe.renderer;

import com.abhisheksoni.tictactoe.model.game.Game;

public class CommandLineRenderer implements Renderer {
    private final CommandLineRendererHelper commandLineRendererHelper;

    private static CommandLineRenderer instance;

    private CommandLineRenderer() {
        this.commandLineRendererHelper = new CommandLineRendererHelper(this);
    }

    private synchronized static CommandLineRenderer initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new CommandLineRenderer();
    }

    public static CommandLineRenderer getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    @Override
    public void render(String message) {
        System.out.print(message);
    }

    @Override
    public String renderTextPrompt(String message) {
        return commandLineRendererHelper.renderTextPrompt(message);
    }

    @Override
    public boolean renderYnPrompt(String message) {
        return commandLineRendererHelper.renderYnPrompt(message);
    }

    @Override
    public void render(Game game) {
        commandLineRendererHelper.renderGame(game);
    }

    @Override
    public void renderGameClosurePrompt() {
        commandLineRendererHelper.renderGameClosurePrompt();
    }

    @Override
    public void renderGameStopped(Game game) {
        commandLineRendererHelper.renderGameStopped(game);
    }

    @Override
    public void renderEndGameMessage() {
        commandLineRendererHelper.renderEndGameMessage();
    }

    @Override
    public void renderWarning(String message) {
        commandLineRendererHelper.renderWarning(message);
    }
}
