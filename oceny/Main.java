package oceny;

import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("Wybierz jedną z poniższych operacji: ");
        System.out.println("Dodawanie nowej oceny (dodaj) ");
        System.out.println("Wyliczanie średniej ze wszystkich wprowadzonych ocen (srednia) ");
        System.out.println("Wyświetlanie najwyższej oceny cząstkowej (najwyzsza) ");
        System.out.println("Wyświetlanie najniższej oceny cząstkowej (najnizsza) ");
        System.out.println("Zakończenie programu (stop) ");
    }
    private static void noGrades() {
        System.out.println("Brak ocen");
    }

private static void caseDodawanieOceny(GradeList oceny)
{
    oceny.dodajOcene(Interfejs.wejscie("Wprowadz ocene: "));
}
private static void caseSrednia(GradeList oceny) {
    if ((oceny.obliczSrednia() == 0)) {
        System.out.println("brak ocen");
    } else {
        Interfejs.wyjscie("Srednia ocen: ", oceny.obliczSrednia(), 2);
    }
}
    private static void caseNajwyzsza(GradeList oceny) {
        if ((oceny.znajdzMax()==0)) {
            System.out.println("brak ocen");
        }
        else{
            Interfejs.wyjscie("Najwyzsza ocena: " , oceny.znajdzMax(), 1);
        }
    }
    private static void caseNajnizsza(GradeList oceny) {
        if ((oceny.znajdzMin()==0)) {
            System.out.println("brak ocen");
        }
        else{
            Interfejs.wyjscie("Najwyzsza ocena: " , oceny.znajdzMin(), 1);
        }

    }
    public static void main(String[] args) {
    System.out.println("Program służy do obsługi ocen studenta");

        Scanner wyborSkaner = new Scanner(System.in);

        GradeList oceny = new GradeList();
        String wybor;

        boolean exit = false;
        while (!exit) {
            menu();
            wybor = wyborSkaner.nextLine();
            switch (wybor) {
                case "dodaj" -> oceny.dodajOcene(Interfejs.wejscie("Wprowadz ocene: "));
                case "srednia" -> caseSrednia(oceny);
                case "najwyzsza" -> caseNajwyzsza(oceny);
                case "najnizsza" -> caseNajnizsza(oceny);
                case "stop" -> {
                    System.out.println("Zakończenie programu");
                    exit = true;
                }
            }
        }
    }

}
