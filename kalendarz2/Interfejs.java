package kalendarz2;

import java.util.List;
import java.util.Scanner;

public class Interfejs {
    public static double getNumberDouble(String context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(context);
        return scanner.nextDouble();
    }

    public static int getNumberInteger(String context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(context);
        return scanner.nextInt();
    }

    public static String getString(String context) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(context);
        return scanner.nextLine();
    }

    public static void displayNumber(String context, double number) {
        System.out.println(context + String.format("%.2f", number));
    }

    public static void displayNumber(String context, double number, int decimalPlaces) {
        System.out.println(context + String.format("%." + decimalPlaces + "f", number));
    }

    public static int interpretDay(String day) {
        return switch (day.toLowerCase()) {
            case "poniedziałek", "pon", "1" -> 0;
            case "wtorek", "wt", "2" -> 1;
            case "środa", "śr", "3" -> 2;
            case "czwartek", "czw", "4" -> 3;
            case "piątek", "pią", "5" -> 4;
            case "sobota", "sob", "6" -> 5;
            case "niedziela", "nied", "7" -> 6;
            default -> 0;
        };
    }
}