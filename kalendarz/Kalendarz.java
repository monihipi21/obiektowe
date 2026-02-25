package kalendarz;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kalendarz {
    private final HashMap<Integer, List<Spotkanie>> kalendarz = new HashMap<>();

    public void dodajSpotkanie(int dzien, Spotkanie spotkanie) {
        kalendarz.putIfAbsent(dzien, new ArrayList<>());
        kalendarz.get(dzien).add(spotkanie);
    }

    public boolean usunSpotkanie(int dzien, String nazwa) {
        if (kalendarz.containsKey(dzien)) {
            List<Spotkanie> spotkania = kalendarz.get(dzien);
            for (int i = 0; i < spotkania.size(); i++) {
                if (spotkania.get(i).getNazwa().equalsIgnoreCase(nazwa)) {
                    spotkania.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Spotkanie> wyswietlSpotkaniaPoPriorytecie(int dzien, Priorytet priorytet) {
        List<Spotkanie> wynik = new ArrayList<>();
        if (kalendarz.containsKey(dzien)) {
            List<Spotkanie> spotkania = kalendarz.get(dzien);
            for (Spotkanie spotkanie : spotkania) {
                if (spotkanie.getPriorytet() == priorytet) {
                    wynik.add(spotkanie);
                }
            }
        }
        return wynik;
    }
    public List<Spotkanie> wyswietlSpotkaniaOdGodziny(int dzien, LocalTime czas) {
        List<Spotkanie> wynik = new ArrayList<>();
        if (kalendarz.containsKey(dzien)) {
            List<Spotkanie> spotkania = kalendarz.get(dzien);
            for (Spotkanie spotkanie : spotkania) {
                if (spotkanie.getCzasRozpoczecia().compareTo(czas) >= 0) {
                    wynik.add(spotkanie);
                }
            }
        }
        return wynik;
    }

    public void dodajGotoweSpotkania() {
        dodajSpotkanie(1, new Spotkanie("Spotkanie A", LocalTime.of(9, 0), LocalTime.of(10, 0), Priorytet.normalny));
        dodajSpotkanie(1, new Spotkanie("Spotkanie B", LocalTime.of(16, 30), LocalTime.of(18, 30), Priorytet.wysoki));
        dodajSpotkanie(1, new Spotkanie("Spotkanie C", LocalTime.of(7, 0), LocalTime.of(8, 0), Priorytet.najwyzszy));
        dodajSpotkanie(1, new Spotkanie("Spotkanie D", LocalTime.of(11, 30), LocalTime.of(12, 30), Priorytet.wysoki));
        dodajSpotkanie(1, new Spotkanie("Spotkanie E", LocalTime.of(13, 0), LocalTime.of(14, 0), Priorytet.normalny));

        dodajSpotkanie(3, new Spotkanie("Spotkanie A", LocalTime.of(11, 30), LocalTime.of(12, 30), Priorytet.wysoki));
        dodajSpotkanie(3, new Spotkanie("Spotkanie B", LocalTime.of(9, 0), LocalTime.of(10, 0), Priorytet.najwyzszy));
        dodajSpotkanie(3, new Spotkanie("Spotkanie C", LocalTime.of(12, 30), LocalTime.of(13, 30), Priorytet.wysoki));
        dodajSpotkanie(3, new Spotkanie("Spotkanie D", LocalTime.of(14, 0), LocalTime.of(16, 0), Priorytet.normalny));
        dodajSpotkanie(3, new Spotkanie("Spotkanie E", LocalTime.of(7, 30), LocalTime.of(9, 30), Priorytet.najwyzszy));
        dodajSpotkanie(3, new Spotkanie("Spotkanie F", LocalTime.of(15, 0), LocalTime.of(16, 0), Priorytet.normalny));

        dodajSpotkanie(2, new Spotkanie("Spotkanie A", LocalTime.of(8, 30), LocalTime.of(9, 30), Priorytet.normalny));
        dodajSpotkanie(2, new Spotkanie("Spotkanie B", LocalTime.of(9, 0), LocalTime.of(10, 0), Priorytet.normalny));
        dodajSpotkanie(2, new Spotkanie("Spotkanie C", LocalTime.of(11, 30), LocalTime.of(12, 30), Priorytet.wysoki));
        dodajSpotkanie(2, new Spotkanie("Spotkanie D", LocalTime.of(13, 0), LocalTime.of(15, 0), Priorytet.najwyzszy));
        dodajSpotkanie(2, new Spotkanie("Spotkanie E", LocalTime.of(15, 30), LocalTime.of(17, 30), Priorytet.wysoki));
        dodajSpotkanie(2, new Spotkanie("Spotkanie F", LocalTime.of(10, 0), LocalTime.of(11, 0), Priorytet.normalny));
        dodajSpotkanie(2, new Spotkanie("Spotkanie G", LocalTime.of(11, 30), LocalTime.of(13, 30), Priorytet.wysoki));
    }
}
