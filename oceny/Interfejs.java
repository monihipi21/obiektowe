package oceny;

import java.util.Scanner;

public class Interfejs {
    public static double wejscie(String inputString){
        System.out.println(inputString);
        Scanner skaner = new Scanner(System.in);
        return skaner.nextDouble();
    }
    public static void wyjscie(String context, double value, int precision){
        System.out.println(context + String.format("%." + precision + "f", value));
    }
}
