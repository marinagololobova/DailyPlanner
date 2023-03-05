package dailyPlanner;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 1;
    private String title;
    private final Type type;
    private final int id;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentException {
        this.title = checkValue(title);
        this.description = checkValue(description);
        this.type = type;
        this.dateTime = dateTime;
        this.id = idGenerator++;
    }

    public static String checkValue (String v) throws IncorrectArgumentException {
        if (v == null || v.isEmpty() || v.isBlank()) {
            throw new IncorrectArgumentException("Некорректный ввод, попробуйте еще раз");
        } else {
            return v;
        }
    }

    public boolean appearsIn(LocalDateTime dateTime) {
        return false;
    }


    public int getIdGenerator() {
        return idGenerator;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }


    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void setIdGenerator(int idGenerator) {
        Task.idGenerator = idGenerator;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && type == task.type && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }

    @Override
    public String toString() {
        return dateTime + " " + title + " описание задачи: " + description + ", тип: " + type + ", id: " + id;
    }
}
