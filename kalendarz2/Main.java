package kalendarz2;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("\nWybierz operację:");
        System.out.println("1. Dodaj spotkanie");
        System.out.println("2. Dodaj zadanie");
        System.out.println("3. Usuń spotkanie");
        System.out.println("4. Usuń zadanie");
        System.out.println("5. Wyświetl wszystkie spotkania jednego dnia o priorytecie");
        System.out.println("6. Wyświetl wszystkie zadania jednego dnia o statusie");
        System.out.println("7. Wyświetl wszystkie spotkania jednego dnia od czasu z priorytetem");
        System.out.println("8. Wyświetl wszystkie zadanie jednego dnia do czasu z statusem");
        System.out.println("9. Dodaj przekladowe spotkania i zadanie");
        System.out.println("10. Wyjście");
        System.out.print("\nProszę podać wybraną opcję: ");
    }

    public static void main(String[] args) {
        WeeklyCalendar weeklyCalendar = new WeeklyCalendar();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Kalendarz tygodniowy");
        do {
            menu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addMeeting(weeklyCalendar);
                case 2 -> addTask(weeklyCalendar);
                case 3 -> removeMeeting(weeklyCalendar);
                case 4 -> removeTask(weeklyCalendar);
                case 5 -> displayMeetingByStatus(weeklyCalendar);
                case 6 -> displayTaskByPriority(weeklyCalendar);
                case 7 -> displayMeetingsFromTime(weeklyCalendar);
                case 8 -> displayTaskFromTime(weeklyCalendar);
                case 9 -> Exaples(weeklyCalendar);
                case 10 -> System.out.println("\n\nBye!");
                default -> System.out.println("\nNieprawidłowy wybór");
            }
        } while (choice != 10);
    }

    private static void addTask(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień zadania (pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        String name = Interfejs.getString("Podaj nazwe zadania: ");

        int startHour = Interfejs.getNumberInteger("Podaj godzinę rozpoczęcia: ");
        int startMinute = Interfejs.getNumberInteger("Podaj minutę rozpoczęcia: ");
        LocalTime startTime = LocalTime.of(startHour, startMinute);
        if (startTime.isBefore(Meeting.EARLIEST_START_TIME)) {
            System.out.println("Spotkanie nie może zaczynać się wcześniej niż " + Meeting.EARLIEST_START_TIME);
            return;
        }

        int endHour = Interfejs.getNumberInteger("Podaj godzinę zakończenia: ");
        int endMinute = Interfejs.getNumberInteger("Podaj minutę zakończenia: ");
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        String statusInput = Interfejs.getString("Podaj status (planowane(1), potwierdzone(2): ");
        Status status = Status.fromString(statusInput);

        weeklyCalendar.addTask(day, new Task(name, startTime, endTime, status));
    }

    private static void addMeeting(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień spotkania(pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        String name = Interfejs.getString("Podaj nazwe spotkania: ");

        int startHour = Interfejs.getNumberInteger("Podaj godzinę rozpoczęcia: ");
        int startMinute = Interfejs.getNumberInteger("Podaj minutę rozpoczęcia: ");
        LocalTime startTime = LocalTime.of(startHour, startMinute);
        if (startTime.isBefore(Meeting.EARLIEST_START_TIME)) {
            System.out.println("Spotkanie nie może zaczynać się wcześniej niż " + Meeting.EARLIEST_START_TIME);
            return;
        }

        int endHour = Interfejs.getNumberInteger("Podaj godzinę zakończenia: ");
        int endMinute = Interfejs.getNumberInteger("Podaj minutę zakończenia: ");
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        String priorityInput = Interfejs.getString("Podaj priorytet (normalny(1), wysoki(2), najwyższy(3): ");
        Priority priority = Priority.fromString(priorityInput);

        weeklyCalendar.addMeeting(day, new Meeting(name, startTime, endTime, priority));
    }

    private static void removeTask(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień zadania ( pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        System.out.println(weeklyCalendar.displayTask(day));

        String name = Interfejs.getString("Podaj nazwę spotkania do usunięcia: ");

        weeklyCalendar.removeTask(day, name);
        System.out.println("Usunięto spotkanie: " + name);
    }

    private static void removeMeeting(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień spotkania (pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        System.out.println(weeklyCalendar.displayMeeting(day));

        String name = Interfejs.getString("Podaj nazwę spotkania do usunięcia: ");

        weeklyCalendar.removeMeeting(day, name);
        System.out.println("Usunięto spotkanie: " + name);
    }

    private static void displayMeetingByStatus(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień spotkania (pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        String priorityInput = Interfejs.getString("Podaj priorytet (normalny(1), wysoki(2), najwyższy(3): ");
        Priority priority = Priority.fromString(priorityInput);

        System.out.println(weeklyCalendar.getMeetingByPriority(day, priority));
    }

    private static void displayTaskByPriority(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień zadania (pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        String statusInput = Interfejs.getString("Podaj status (planowane(1), potwierdzone(2): ");
        Status status = Status.fromString(statusInput);

        System.out.println(weeklyCalendar.getTaskByStatus(day, status));
    }

    private static void displayMeetingsFromTime(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj w który dzień (pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        String priorityInput = Interfejs.getString("Podaj priorytet (normalny(1), wysoki(2), najwyższy(3): ");
        Priority priority = Priority.fromString(priorityInput);

        int hour = Interfejs.getNumberInteger("Podaj godzinę rozpoczęcia: ");
        int minute = Interfejs.getNumberInteger("Podaj minutę rozpoczęcia: ");
        LocalTime time = LocalTime.of(hour, minute);

        System.out.println(weeklyCalendar.getMeetingsFromTime(day, priority, time));
    }

    private static void displayTaskFromTime(WeeklyCalendar weeklyCalendar) {
        String dayInput = Interfejs.getString("\nPodaj dzień zadania (pon(1), wt(2), śr(3), czw(4), pią(5), sob(6), nied(7)): ");
        int day = Interfejs.interpretDay(dayInput);

        String statusInput = Interfejs.getString("Podaj status (planowane(1), potwierdzone(2): ");
        Status status = Status.fromString(statusInput);

        int hour = Interfejs.getNumberInteger("Podaj godzinę zakończenia: ");
        int minute = Interfejs.getNumberInteger("Podaj minutę zakończenia: ");
        LocalTime time = LocalTime.of(hour, minute);

        System.out.println(weeklyCalendar.getTaskFromTime(day, status, time));
    }

    private static void Exaples(WeeklyCalendar weeklyCalendar) {

        weeklyCalendar.addTask(Interfejs.interpretDay("2"), new Task("zadanieA", LocalTime.of(11, 00),LocalTime.of(13, 00), Status.fromString("1")));
        weeklyCalendar.addTask(Interfejs.interpretDay("2"), new Task("zadanieB", LocalTime.of(9, 00),LocalTime.of(12, 30), Status.fromString("2")));
        weeklyCalendar.addMeeting(Interfejs.interpretDay("2"), new Meeting("spotkanieA", LocalTime.of(11, 00),LocalTime.of(12, 00), Priority.fromString("1")));
        weeklyCalendar.addMeeting(Interfejs.interpretDay("2"), new Meeting("spotkanieB", LocalTime.of(14, 00),LocalTime.of(16, 30), Priority.fromString("2")));
        weeklyCalendar.addMeeting(Interfejs.interpretDay("2"), new Meeting("spotkanieC", LocalTime.of(14, 00),LocalTime.of(16, 00), Priority.fromString("3")));

        weeklyCalendar.addTask(Interfejs.interpretDay("3"), new Task("zadanieA", LocalTime.of(12, 00),LocalTime.of(13, 00), Status.fromString("1")));
        weeklyCalendar.addTask(Interfejs.interpretDay("3"), new Task("ZadanieB", LocalTime.of(9, 00),LocalTime.of(12, 30), Status.fromString("2")));
        weeklyCalendar.addMeeting(Interfejs.interpretDay("3"), new Meeting("spotkanieA", LocalTime.of(11, 00),LocalTime.of(12, 00), Priority.fromString("1")));
        weeklyCalendar.addMeeting(Interfejs.interpretDay("3"), new Meeting("spotkanieB", LocalTime.of(14, 00),LocalTime.of(16, 30), Priority.fromString("2")));
        weeklyCalendar.addMeeting(Interfejs.interpretDay("3"), new Meeting("spotkanieC", LocalTime.of(14, 00),LocalTime.of(16, 00), Priority.fromString("3")));

        System.out.println("dodano przykladowe");
    }
}

