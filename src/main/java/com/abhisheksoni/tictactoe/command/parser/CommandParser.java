package com.abhisheksoni.tictactoe.command.parser;

import com.abhisheksoni.tictactoe.command.type.Command;

import java.util.StringTokenizer;

public class CommandParser {
    private final CommandParserHelper commandParserHelper;
    private static CommandParser instance;

    private CommandParser() {
        this.commandParserHelper = new CommandParserHelper();
    }

    private synchronized static CommandParser initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new CommandParser();
    }

    public static CommandParser getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    public Command parse(String[] arguments) {
        return commandParserHelper.parse(arguments);
    }

    public Command parse(String arguments) {
        return parse(tokenizeArguments(arguments));
    }

    private String[] tokenizeArguments(String stringArguments) {
        StringTokenizer stringTokenizer = new StringTokenizer(stringArguments);
        String[] arguments = new String[stringTokenizer.countTokens()];
        int index = 0;
        while (stringTokenizer.hasMoreTokens()) {
            arguments[index++] = stringTokenizer.nextToken();
        }
        return arguments;
    }
}
