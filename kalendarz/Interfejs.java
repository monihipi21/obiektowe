package kalendarz;

import java.util.Scanner;

public class Interfejs {
    private static Scanner skaner = new Scanner(System.in);

    public static int pobierzLiczbeCalkowita(String kontekst) {
        System.out.print(kontekst);
        return skaner.nextInt();
    }

    public static String pobierzTekst(String kontekst) {
        System.out.print(kontekst);
        skaner.nextLine();
        return skaner.nextLine();
    }
}
