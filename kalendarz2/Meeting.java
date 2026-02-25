package kalendarz2;

import java.time.LocalTime;

final class Meeting extends Event {
    private Priority priority;

    public Meeting(String name, LocalTime startTime, LocalTime endTime, Priority priority) {
        super(name, startTime, endTime);
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public String toString() {
        return "Spotkanie: " + name + ", " + startTime + " - " + endTime + " Pryorytet: " + priority;
    }
}

enum Priority {
    NORMALNY(1),
    WYSOKI(2),
    NAJWYŻSZY(3);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static Priority fromString(String value) {
        switch (value.toLowerCase()) {
            case "normalny", "1" -> {return NORMALNY;}
            case "wysoki", "2" -> {return WYSOKI;}
            case "najwyższy", "3" -> {return NAJWYŻSZY;}
            default -> {return null;}
        }
    }
}
