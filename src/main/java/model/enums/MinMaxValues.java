package model.enums;

public class MinMaxValues {

    private MinMaxValues() {
    }

    public static final int MAX_VALUE = 10;
    public static final int MIN_VALUE = 0;

    public static int checkMinMaxValue(int value) {
        return Math.max(Math.min(MAX_VALUE, value), MIN_VALUE);
    }
}
