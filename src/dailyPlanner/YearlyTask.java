package dailyPlanner;

import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, description, type, dateTime);
    }

    public boolean appearsIn(LocalDateTime dateTime) {
        return getDateTime().getYear() == dateTime.getYear();
    }
}
