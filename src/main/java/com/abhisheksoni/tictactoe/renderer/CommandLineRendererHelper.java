package com.abhisheksoni.tictactoe.renderer;

import com.abhisheksoni.tictactoe.input.InputControl;
import com.abhisheksoni.tictactoe.model.game.*;
import com.abhisheksoni.tictactoe.model.player.Player;

public class CommandLineRendererHelper {
    private static final String LEFT_MARGIN = "                  ";
    private final Renderer renderer;
    private final InputControl inputControl;
    public CommandLineRendererHelper(Renderer renderer) {
        this.renderer = renderer;
        this.inputControl = InputControl.getInstance();
    }

    public void renderGame(Game game) {
        renderGameWithoutPrompt(game);
        renderCommandPrompt();
    }

    public void renderGameWithoutPrompt(Game game) {
        clearConsole();
        nextLine();
        renderGameHeading(game);
        nextLine();
        renderBoard(game.getBoard());
        nextLine();
        renderGameWarning(game);
    }

    private void renderGameWarning(Game game) {
        GamePlayMetadata gamePlayMetadata = game.getGamePlayMetadata();
        String warningMessage = gamePlayMetadata.getWarningMessage();
        if (warningMessage == null) {
            nextLine();
            return;
        }
        gamePlayMetadata.setWarningMessage(null);
        renderWarning(warningMessage);
        nextLine();
    }

    private void renderCommandPrompt() {
        render("\tCOMMAND: ");
    }

    private void clearConsole() {
        render("\033[H\033[2J");
        System.out.flush();
    }

    private void renderBoard(Board board) {
        Cell[][] cells = board.getCells();
        String padding = " ";
        render(LEFT_MARGIN + "      ");
        for (int index = 0; index < cells.length; index++) {
            if (index == cells.length - 1) {
                render(index + "    ");
            } else {
                render(index + "       ");
            }
        }
        nextLine();
        drawMatrixLine(cells);
        nextLine();
        for (int index = 0; index < cells.length; index++) {
            renderCellsRow(cells, index, LEFT_MARGIN, padding);
            nextLine();
            drawMatrixLine(cells);
            nextLine();
        }
    }

    private void drawMatrixLine(Cell[][] cells) {
        render("                    +");
        for (int index = 0; index < cells.length; index++) {
            render("-------+");
        }
    }

    private void drawLine(Game game, char symbol) {
        int lineUnitLength = 8;
        render(repeatChar(symbol, LEFT_MARGIN.length() + 3));
        for (int index = 0; index < game.getBoard().getCells().length; index++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(repeatChar(symbol, lineUnitLength));
            render(stringBuilder.toString());
        }
    }

    private String repeatChar(char input, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < count; index++) {
            stringBuilder.append(input);
        }
        return stringBuilder.toString();
    }

    private void renderCellsRow(Cell[][] cells, int rowIndex, String leftMargin, String padding) {
        render(leftMargin + rowIndex + " |");
        for (Cell cell : cells[rowIndex]) {
            render(padding);
            render("  " + cell.getSymbol() + "  ");
            render(" |");
        }
    }

    private void nextLine() {
        System.out.println();
    }

    private void renderGameHeading(Game game) {
        drawLine(game, '*');
        nextLine();
        render("\t\t| TIC TAC TOE |");
        nextLine();
        drawLine(game, '*');
        nextLine();
        render("Help-\n");
        render("\t\tPlay Move: row col\n");
        render("\t\tUndo Move: u\n");
        render("\t\tReset Game: r\n");
        render("\t\tNew Name: n\n");
        render("\t\tExit Game: e\n");
        render("Players-\n");
        Player activePlayer = game.getGamePlayMetadata().getActivePlayer();
        for (int index = 0; index < game.getPlayers().size(); index++) {
            Player player = game.getPlayers().get(index);
            render("\t\t");
            if (player.getId().equals(activePlayer.getId())) {
                render(player + " <--- IN PLAY");
            } else {
                render(player.toString());
            }
            render("\n");
        }
    }

    public boolean renderYnPrompt(String message) {
        render("\n\t" + message);
        String yOrNInput = inputControl.string();
        return (yOrNInput.length() == 1 && "y".equalsIgnoreCase(yOrNInput));
    }

    public String renderTextPrompt(String message) {
        render("\n\t" + message);
        return inputControl.string();
    }

    public void renderWarning(String message) {
        render("\t------>  " + message);
    }

    private void render(String message) {
        renderer.render(message);
    }

    public void renderGameClosurePrompt() {
        nextLine();
        renderWarning("Your game has ended. You may reset, play a new game or exit the game");
        nextLine();
        renderCommandPrompt();
    }

    public void renderGameStopped(Game game) {
        renderGameWithoutPrompt(game);
        if (game.getGameState() == GameState.WON) {
            String activePlayerId = game.getGamePlayMetadata().getActivePlayer().getId();
            renderCongratulationsMessage(activePlayerId);
        } else if (game.getGameState() == GameState.DRAW) {
            renderGameDrawMessage();
        }
        nextLine();
    }

    private void renderGameDrawMessage() {
        render("\t>>>>>>>  It's a draw!  <<<<<<<");
    }

    private void renderCongratulationsMessage(String playerId) {
        String message = "CONGRATULATIONS! " + playerId + " WON THE GAME!";
        render("\t>>>>>>>  " + message + "  <<<<<<<");
    }

    public void renderEndGameMessage() {
        nextLine();
        render("\tThank You for playing! See you later!");
        nextLine();
    }
}
