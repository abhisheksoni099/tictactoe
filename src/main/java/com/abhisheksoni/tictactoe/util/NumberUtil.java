package com.abhisheksoni.tictactoe.util;

public class NumberUtil {
    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNotNumeric(String input) {
        return !isNumeric(input);
    }
}
