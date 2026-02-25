package kalendarz3;

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
    System.out.println("8. Wyświetl wszystkie zadania jednego dnia do czasu z statusem");
    System.out.println("9. Dodaj przykładowe wartości do kalendarza");
    System.out.println("10. Wyjście");
    System.out.print("\nProszę podać wybraną opcję: ");
  }

  public static void main(String[] args) {
    WeeklyCalendar weeklyCalendar = new WeeklyCalendar();
    Scanner scanner = new Scanner(System.in);
    int choice;

    System.out.println("Program-Tygodniowy Kalendarz spotkań i zadań");
    do {
      menu();
      choice = scanner.nextInt();

      switch (choice) {
        case 1 -> addMeeting(weeklyCalendar);
        case 2 -> addTask(weeklyCalendar);
        case 3 -> removeMeeting(weeklyCalendar);
        case 4 -> removeTask(weeklyCalendar);
        case 5 -> displayMeetingByPriority(weeklyCalendar);
        case 6 -> displayTaskByStatus(weeklyCalendar);
        case 7 -> displayMeetingsFromTime(weeklyCalendar);
        case 8 -> displayTaskFromTime(weeklyCalendar);
        case 9 -> developerDate(weeklyCalendar);
        case 10 -> System.out.println("\n\nZakończono program");
        default -> System.out.println("\nNieprawidłowy wybór");
      }
    } while (choice != 10);
  }

  private static void addTask(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    String name = Interfejs.getString("Podaj nazwę zadania: ");
    int startHour = Interfejs.getNumberInteger("Podaj godzinę rozpoczęcia: ");
    int startMinute = Interfejs.getNumberInteger("Podaj minutę rozpoczęcia: ");

    if (startHour < Event.EARLIEST_START_TIME.getHour()) {
      System.out.println("Zadanie nie może zaczynać się wcześniej niż " + Meeting.EARLIEST_START_TIME);
      return;
    }

    LocalTime startTime = LocalTime.of(startHour, startMinute);
    int endHour = Interfejs.getNumberInteger("Podaj godzinę zakończenia: ");
    int endMinute = Interfejs.getNumberInteger("Podaj minutę zakończenia: ");
    LocalTime endTime = LocalTime.of(endHour, endMinute);
    String statusInput = Interfejs.getString("Podaj status (1-planowane, 2-potwierdzone): ");
    Status status = Status.fromString(statusInput);

    weeklyCalendar.addEvent(day, new Task(name, startTime, endTime, status));
  }

  private static void addMeeting(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    String name = Interfejs.getString("Podaj nazwę spotkania: ");
    int startHour = Interfejs.getNumberInteger("Podaj godzinę rozpoczęcia: ");
    int startMinute = Interfejs.getNumberInteger("Podaj minutę rozpoczęcia: ");

    if (startHour < Meeting.EARLIEST_START_TIME.getHour()) {
      System.out.println("Spotkanie nie może zaczynać się wcześniej niż " + Meeting.EARLIEST_START_TIME);
      return;
    }

    LocalTime startTime = LocalTime.of(startHour, startMinute);
    int endHour = Interfejs.getNumberInteger("Podaj godzinę zakończenia: ");
    int endMinute = Interfejs.getNumberInteger("Podaj minutę zakończenia: ");
    LocalTime endTime = LocalTime.of(endHour, endMinute);
    String priorityInput = Interfejs.getString("Podaj priorytet (1-normalny, 2-wysoki, 3-najwyższy): ");
    Priority priority = Priority.fromString(priorityInput);

    weeklyCalendar.addEvent(day, new Meeting(name, startTime, endTime, priority));
  }

  private static void removeTask(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    System.out.println(weeklyCalendar.filterEvents(day, event -> event instanceof Task).toString());
    String name = Interfejs.getString("Podaj nazwę zadania do usunięcia: ");
    weeklyCalendar.removeEvent(day, event -> event instanceof Task && event.getName().equals(name));
  }

  private static void removeMeeting(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    System.out.println(weeklyCalendar.filterEvents(day, event -> event instanceof Meeting).toString());
    String name = Interfejs.getString("Podaj nazwę spotkania do usunięcia: ");
    weeklyCalendar.removeEvent(day, event -> event instanceof Meeting && event.getName().equals(name));
  }

  private static void displayMeetingByPriority(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    String priorityInput = Interfejs.getString("Podaj priorytet (1-normalny, 2-wysoki, 3-najwyższy): ");
    Priority priority = Priority.fromString(priorityInput);
    System.out.println(weeklyCalendar.filterEvents(day, event -> event instanceof Meeting && ((Meeting) event).getPriority() == priority).toString());
  }

  private static void displayTaskByStatus(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    String statusInput = Interfejs.getString("Podaj status (1-planowane, 2-potwierdzone): ");
    Status status = Status.fromString(statusInput);
    System.out.println(weeklyCalendar.filterEvents(day, event -> event instanceof Task && ((Task) event).getStatus() == status).toString());
  }

  private static void displayMeetingsFromTime(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    String priorityInput = Interfejs.getString("Podaj priorytet (1-normalny, 2-wysoki, 3-najwyższy): ");
    Priority priority = Priority.fromString(priorityInput);
    int hour = Interfejs.getNumberInteger("Podaj godzinę rozpoczęcia: ");
    int minute = Interfejs.getNumberInteger("Podaj minutę rozpoczęcia: ");
    LocalTime time = LocalTime.of(hour, minute);
    System.out.println(weeklyCalendar.filterEvents(day, event -> event instanceof Meeting && ((Meeting) event).getPriority() == priority && !event.getStartTime().isBefore(time)).toString());
  }

  private static void displayTaskFromTime(WeeklyCalendar weeklyCalendar) {
    String dayInput = Interfejs.getString("\nPodaj dzień: ");
    int day = Interfejs.interpretDay(dayInput);
    String statusInput = Interfejs.getString("Podaj status (1-planowane, 2-potwierdzone): ");
    Status status = Status.fromString(statusInput);
    int hour = Interfejs.getNumberInteger("Podaj godzinę zakończenia: ");
    int minute = Interfejs.getNumberInteger("Podaj minutę zakończenia: ");
    LocalTime time = LocalTime.of(hour, minute);
    System.out.println(weeklyCalendar.filterEvents(day, event -> event instanceof Task && ((Task) event).getStatus() == status && !event.getEndTime().isAfter(time)).toString());
  }

  private static void developerDate(WeeklyCalendar weeklyCalendar) {
   //przykładowe zadania i spotkania na dzień 1 i 2
    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Task("ZadanieA", LocalTime.of(9, 0), LocalTime.of(10, 0), Status.PLANOWANE));
    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Task("ZadanieB", LocalTime.of(12, 0), LocalTime.of(14, 0), Status.POTWIERDZONE));
    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Task("ZadanieC", LocalTime.of(11, 0), LocalTime.of(12, 30), Status.PLANOWANE));
    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Task("ZadanieD", LocalTime.of(14, 0), LocalTime.of(16, 30), Status.POTWIERDZONE));
    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Task("ZadanieE", LocalTime.of(10, 0), LocalTime.of(11, 30), Status.PLANOWANE));


    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Meeting("SpotkanieA", LocalTime.of(10, 30), LocalTime.of(11, 30), Priority.WYSOKI));
    weeklyCalendar.addEvent(Interfejs.interpretDay("1"),
            new Meeting("SpotkanieB", LocalTime.of(14, 0), LocalTime.of(15, 0), Priority.NORMALNY));
    weeklyCalendar.addEvent(Interfejs.interpretDay("2"),
            new Meeting("SpotkanieC", LocalTime.of(16, 0), LocalTime.of(17, 30), Priority.NAJWAŻNIEJSZY));
    weeklyCalendar.addEvent(Interfejs.interpretDay("2"),
            new Meeting("SpotkanieD", LocalTime.of(9, 30), LocalTime.of(10, 30), Priority.WYSOKI));
    weeklyCalendar.addEvent(Interfejs.interpretDay("2"),
            new Meeting("SpotkanieE", LocalTime.of(12, 0), LocalTime.of(13, 0), Priority.NORMALNY));

    System.out.println("Dodano przykładowe zadania i spotkania");
  }
}
