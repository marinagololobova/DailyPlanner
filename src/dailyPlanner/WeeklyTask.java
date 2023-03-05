package dailyPlanner;

import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, description, type, dateTime);
    }

    public boolean appearsIn(LocalDateTime dateTime) {
        return getDateTime().getDayOfWeek().equals(dateTime.getDayOfWeek());
    }
}
