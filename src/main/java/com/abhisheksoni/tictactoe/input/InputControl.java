package com.abhisheksoni.tictactoe.input;

import java.util.Scanner;

public class InputControl {
    private static InputControl instance;

    private InputControl() {
    }

    private synchronized static InputControl initializeInstance() {
        if (instance != null) {
            return instance;
        }
        return new InputControl();
    }

    public static InputControl getInstance() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    public String string() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
