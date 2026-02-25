package kalendarz3;

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

    @Override
    public String toString() {
        return "Spotkanie: " + name + ", od " + startTime + " do " + endTime + ", Priorytet: " + priority;
    }
}

enum Priority {
    NORMALNY(1),
    WYSOKI(2),
    NAJWAŻNIEJSZY(3);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static Priority fromString(String value) {
        return switch (value.toLowerCase()) {
            case "normalny", "1" -> NORMALNY;
            case "wysoki", "2" -> WYSOKI;
            case "najważniejszy", "3" -> NAJWAŻNIEJSZY;
            default -> null;
        };
    }
}
