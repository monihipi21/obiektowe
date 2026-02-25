package kalendarz;

import java.time.LocalTime;

public class Spotkanie {
    private String nazwa;
    private LocalTime czasRozpoczecia;
    private LocalTime czasZakonczenia;
    private Priorytet priorytet;

    public static final LocalTime NAJWCZESNIEJSZA_GODZINA_ROZPOCZECIA = LocalTime.of(6, 0);

    public Spotkanie(String nazwa, LocalTime czasRozpoczecia, LocalTime czasZakonczenia, Priorytet priorytet) {
        this.nazwa = nazwa;
        this.czasRozpoczecia = czasRozpoczecia;
        this.czasZakonczenia = czasZakonczenia;
        this.priorytet = priorytet;
    }

    public String getNazwa() {
        return nazwa;
    }

    public LocalTime getCzasRozpoczecia() {
        return czasRozpoczecia;
    }

    public LocalTime getCzasZakonczenia() {
        return czasZakonczenia;
    }

    public Priorytet getPriorytet() {
        return priorytet;
    }

    public String toString() {
        return "Spotkanie: " + nazwa + ", " + czasRozpoczecia + " - " + czasZakonczenia + ", Priorytet: " + priorytet;
    }
}

enum Priorytet {
    normalny,
    wysoki,
    najwyzszy;
}
