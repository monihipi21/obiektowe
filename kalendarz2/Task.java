package kalendarz2;

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

    public String toString() {
        return "Zadanie: " + name + ", " + startTime + " - " + endTime + " Status: " + status;
    }
}

enum Status {
    PLANOWANE(1),
    POTWIEDZONE(2);

    private final int level;

    Status(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static Status fromString(String value) {
        switch (value.toLowerCase()) {
            case "planowane", "1" -> {return PLANOWANE;}
            case "potwierdzone", "2" -> {return POTWIEDZONE;}
            default -> {return null;}
        }
    }
}