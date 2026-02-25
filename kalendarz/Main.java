package kalendarz;

import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Kalendarz kalendarz = new Kalendarz();
        boolean running = true;

        while (running) {
            System.out.println("\n Kalendarz tygodniowy spotkań ");
            System.out.println("1. Dodaj spotkanie");
            System.out.println("2. Usuń spotkanie");
            System.out.println("3. Wyświetl spotkania po priorytecie");
            System.out.println("4. Wyświetl spotkania od godziny");
            System.out.println("5. Wyjdź");
            int wybor = Interfejs.pobierzLiczbeCalkowita("Wybierz opcję: ");

            switch (wybor) {
                    case 1 -> {
                        int dzien = Interfejs.pobierzLiczbeCalkowita("Podaj dzień (1-7): ");
                        String nazwa = Interfejs.pobierzTekst("Podaj nazwę spotkania: ");
                        LocalTime czasRozpoczecia = LocalTime.of(
                                Interfejs.pobierzLiczbeCalkowita("Podaj godzinę rozpoczęcia: "),
                                Interfejs.pobierzLiczbeCalkowita("Podaj minutę rozpoczęcia: "));
                        LocalTime czasZakonczenia = LocalTime.of(
                                Interfejs.pobierzLiczbeCalkowita("Podaj godzinę zakończenia: "),
                                Interfejs.pobierzLiczbeCalkowita("Podaj minutę zakończenia: "));

                        if (czasRozpoczecia.isBefore(Spotkanie.NAJWCZESNIEJSZA_GODZINA_ROZPOCZECIA)) {
                            System.out.println("Spotkanie nie może rozpocząć się wcześniej niż " + Spotkanie.NAJWCZESNIEJSZA_GODZINA_ROZPOCZECIA);
                        } else {
                            Priorytet priorytet = Priorytet.valueOf(
                                    Interfejs.pobierzTekst("Podaj priorytet (normalny/wysoki/najwyzszy): ").toLowerCase());
                            kalendarz.dodajSpotkanie(dzien, new Spotkanie(nazwa, czasRozpoczecia, czasZakonczenia, priorytet));
                            System.out.println("Dodano spotkanie");
                        }
                    }

                    case 2 -> {
                    int dzien = Interfejs.pobierzLiczbeCalkowita("Podaj dzień: ");
                    String nazwa = Interfejs.pobierzTekst("Podaj nazwę spotkania: ");
                    if (kalendarz.usunSpotkanie(dzien, nazwa)) {
                        System.out.println("Usunięto spotkanie");
                    } else {
                        System.out.println("Nie znaleziono spotkania");
                    }
                }
                case 3 -> {
                    int dzien = Interfejs.pobierzLiczbeCalkowita("Podaj dzień: ");
                    Priorytet priorytet = Priorytet.valueOf(
                            Interfejs.pobierzTekst("Podaj priorytet (normalny/wysoki/najwyzszy): ").toLowerCase());
                    List<Spotkanie> spotkania = kalendarz.wyswietlSpotkaniaPoPriorytecie(dzien, priorytet);
                    if (spotkania.isEmpty()) {
                        System.out.println("Brak spotkań o podanym priorytecie");
                    } else {
                        spotkania.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    int dzien = Interfejs.pobierzLiczbeCalkowita("Podaj dzień: ");
                    LocalTime czas = LocalTime.of(
                            Interfejs.pobierzLiczbeCalkowita("Podaj godzinę: "),
                            Interfejs.pobierzLiczbeCalkowita("Podaj minutę: "));
                    List<Spotkanie> spotkania = kalendarz.wyswietlSpotkaniaOdGodziny(dzien, czas);
                    if (spotkania.isEmpty()) {
                        System.out.println("Brak spotkań od podanej godziny");
                    } else {
                        spotkania.forEach(System.out::println);
                    }
                }
                case 5 -> running = false;
                default -> System.out.println("Nieprawidłowy wybór");
            }
        }
    }
}
