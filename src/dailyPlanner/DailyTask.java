package dailyPlanner;

import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, description, type, dateTime);
    }

    public boolean appearsIn(LocalDateTime dateTime) {
        return getDateTime().toLocalDate().equals(dateTime.toLocalDate());

    }
}
