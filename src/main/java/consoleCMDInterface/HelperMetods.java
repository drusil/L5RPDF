package consoleCMDInterface;

import model.enums.MinMaxValues;

import java.util.Scanner;

public class HelperMetods {
    public static final String MIN_MAX_VALUE_MESSAGE = "Pun new value between "
            + MinMaxValues.MIN_VALUE + " and " + MinMaxValues.MAX_VALUE;

    private HelperMetods() {
    }



    public static int scanIntValue(Scanner scanner) {
        return scanIntValue(scanner, 0, 10);
    }


    public static int scanIntValue(Scanner scanner, int minValue, int maxValue) {
        boolean scanSuccesfull = false;
        int value = Integer.MIN_VALUE;
        System.out.println("Put value between " + minValue + " and " + maxValue);
        do {
            try {
                String stringValue = scanner.nextLine();
                if (!stringValue.equals("")) {
                    value = Integer.valueOf(stringValue);
                    if (value >= minValue && value <= maxValue) scanSuccesfull = true;
                    else System.out.println("Wrong value try again");
                } else System.out.println("Try again enter not allowed");

            } catch (NumberFormatException e) {
                System.out.println("Please us number");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Wrong value");
                scanner.next();

            }
        } while (!scanSuccesfull);
        return value;
    }

    public static String scanStringValue(Scanner scanner) {
        boolean scanSuccesfull = false;
        String value = "";
        do {
            try {
                value = scanner.next();
                if (value == null) {
                    value = "";
                }
                scanSuccesfull = true;

            } catch (Exception e) {
                System.out.println("Try Again");
                scanner.next();
            }

        } while (!scanSuccesfull);
        return value;
    }

    public static void separtor() {
        System.out.println("===============================");
    }
}


