package kalendarz3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WeeklyCalendar {
    private final List<List<Event>> days;

    public WeeklyCalendar() {
        days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(new ArrayList<>());
        }
    }
    public void addEvent(int day, Task event) {
        days.get(day).add(event);
    }
    public void addEvent(int day, Meeting event) {
        days.get(day).add(event);
    }
    public void removeEvent(int day, Predicate<Event> filter) {
        days.get(day).removeAll(filterEvents(day, filter));
    }
    public List<Event> filterEvents(int day, Predicate<Event> filter) {
        List<Event> result = new ArrayList<>();
        for (Event event : days.get(day)) {
            if (filter.test(event)) {
                result.add(event);
            }
        }
        return result;
    }
}