package kalendarz3;

import java.time.LocalTime;

final class Task extends Event {
    private Status status;

    public Task(String name, LocalTime startTime, LocalTime endTime, Status status) {
        super(name, startTime, endTime);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Zadanie: " + name + ", od " + startTime + " do " + endTime + ", Status: " + status;
    }
}

enum Status {
    PLANOWANE(1),
    POTWIERDZONE(2);

    private final int level;

    Status(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static Status fromString(String value) {
        return switch (value.toLowerCase()) {
            case "planowane", "1" -> PLANOWANE;
            case "potwierdzone", "2" -> POTWIERDZONE;
            default -> null;
        };
    }
}