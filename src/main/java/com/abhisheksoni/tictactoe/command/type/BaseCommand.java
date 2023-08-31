package com.abhisheksoni.tictactoe.command.type;

import com.abhisheksoni.tictactoe.command.model.CommandValidationResult;
import com.abhisheksoni.tictactoe.command.parser.CommandParser;
import com.abhisheksoni.tictactoe.engine.core.GameCache;
import com.abhisheksoni.tictactoe.engine.core.GameInitializer;
import com.abhisheksoni.tictactoe.input.InputControl;
import com.abhisheksoni.tictactoe.model.game.Game;
import com.abhisheksoni.tictactoe.renderer.CommandLineRenderer;
import com.abhisheksoni.tictactoe.renderer.Renderer;

public abstract class BaseCommand implements Command {
    protected String[] arguments;
    protected Renderer renderer;
    protected GameCache gameCache;
    protected GameInitializer gameInitializer;
    protected InputControl inputControl;
    protected CommandParser commandParser;
    protected Game game;

    protected BaseCommand() {
        this.renderer = CommandLineRenderer.getInstance();
        this.gameCache = GameCache.getInstance();
        this.gameInitializer = GameInitializer.getInstance();
        this.inputControl = InputControl.getInstance();
        this.commandParser = CommandParser.getInstance();
        this.game = this.gameCache.getCurrentGame();
    }

    protected BaseCommand(String[] arguments) {
        this();
        this.arguments = arguments;
    }

    @Override
    public void execute() {
    }

    @Override
    public CommandValidationResult validate() {
        return new CommandValidationResult(true);
    }
}
