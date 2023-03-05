package dailyPlanner;

import java.time.LocalDateTime;

public class OneTimeTask extends Task {

    public OneTimeTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, description, type, dateTime);
    }

    public boolean appearsIn(LocalDateTime dateTime) {
        return getDateTime().toLocalDate().equals(dateTime.toLocalDate());
    }
}
