package kalendarz2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklyCalendar {
    private final Map<Integer, List<Event>> days;

    public WeeklyCalendar() {
        days = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            days.put(i, new ArrayList<>());
        }
    }

    public void addMeeting(int day, Meeting meeting) {
        days.get(day).add(meeting);
    }

    public void addTask(int day, Task task) {
        days.get(day).add(task);
    }

    public void removeMeeting(int day, String name) {
        List<Event> events = days.get(day);
        events.removeIf(event -> event instanceof Meeting && event.getName().equals(name));
    }

    public void removeTask(int day, String name) {
        List<Event> events = days.get(day);
        events.removeIf(event -> event instanceof Task && event.getName().equals(name));
    }

    public List<Meeting> getMeetingByPriority(int day, Priority priority) {
        List<Meeting> result = new ArrayList<>();
        List<Event> events = days.get(day);

        for (Event event : events) {
            if (event instanceof Meeting && ((Meeting) event).getPriority() == priority) {
                result.add((Meeting) event);
            }
        }
        return result;
    }

    public List<Meeting> getMeetingsByPriority(int day, Priority priority) {
        List<Meeting> result = new ArrayList<>();
        for (day = 0; day < 7; day++) {
            result.addAll(getMeetingsByPriority(day, priority));
        }
        return result;
    }


    public List<Task> getTaskByStatus(int day, Status status) {
        List<Task> result = new ArrayList<>();
        List<Event> events = days.get(day);

        for (Event event : events) {
            if (event instanceof Task && ((Task) event).getStatus() == status) {
                result.add((Task) event);
            }
        }
        return result;
    }

    public List<Task> getTasksByStatus(int day, Status status) {
        List<Task> result = new ArrayList<>();
        for (day = 0; day < 7; day++) {
            result.addAll(getTasksByStatus(day, status));
        }
        return result;
    }

    public List<Meeting> getMeetingsFromTime(int day, Priority priority, LocalTime startTime) {
        List<Meeting> result = new ArrayList<>();
        List<Event> events = days.get(day);

        for (Event event : events) {
            if (event instanceof Meeting) {
                Meeting meeting = (Meeting) event;
                if (meeting.getPriority() == priority && meeting.getStartTime().isAfter(startTime)) {
                    result.add(meeting);
                }
            }
        }
        return result;
    }

    public List<Task> getTaskFromTime(int day, Status status, LocalTime startTime) {
        List<Task> result = new ArrayList<>();
        List<Event> events = days.get(day);

        for (Event event : events) {
            if (event instanceof Task) {
                Task task = (Task) event;
                if (task.getStatus() == status && task.getStartTime().isAfter(startTime)) {
                    result.add(task);
                }
            }
        }
        return result;
    }

    public String displayMeeting(int day) {
        List<Event> events = days.get(day);
        StringBuilder result = new StringBuilder();

        for (Event event : events) {
            if (event instanceof Meeting) {
                result.append(event.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public String displayTask(int day) {
        List<Event> events = days.get(day);
        StringBuilder result = new StringBuilder();

        for (Event event : events) {
            if (event instanceof Task) {
                result.append(event.toString()).append("\n");
            }
        }
        return result.toString();
    }
}
