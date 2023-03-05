package dailyPlanner;

import java.time.LocalDateTime;

public class MonthlyTask extends Task{

    public MonthlyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, description, type, dateTime);
    }

    public boolean appearsIn(LocalDateTime dateTime) {
        return getDateTime().getDayOfMonth() == (dateTime.getDayOfMonth());
    }
}
