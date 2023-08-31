package com.abhisheksoni.tictactoe.input;

import com.abhisheksoni.tictactoe.engine.core.GameCache;

import java.util.Queue;
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
        Queue<String> inputs = GameCache.getInstance().getPreloadedInputs();
        if (!inputs.isEmpty()) {
            return inputs.remove();
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
