package com.abhisheksoni.tictactoe;

import com.abhisheksoni.tictactoe.command.parser.CommandParser;
import com.abhisheksoni.tictactoe.command.type.Command;

public class Runner {
    public static void main(String[] arguments) {
        CommandParser commandParser = CommandParser.getInstance();
        Command command = commandParser.parse(arguments);
        command.execute();
    }
}
