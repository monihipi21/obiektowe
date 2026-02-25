package kalendarz3;

import java.time.LocalTime;

abstract sealed class Event permits Meeting, Task {
    protected String name;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public static final LocalTime EARLIEST_START_TIME = LocalTime.of(7, 0);

    public Event(String name, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public abstract String toString();
}