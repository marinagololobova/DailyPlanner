package dailyPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskService {
    private static final Set<Task> taskSet = new HashSet<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название вашей задачи");
            String title = Task.checkValue(scanner.nextLine());
            System.out.println("Введите описание задачи");
            String description = Task.checkValue(scanner.nextLine());
            System.out.println("Выбирите тип задачи: 0 - Рабочая 1 - Личная");
            Type type = Type.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 1 - Однократная, 2 - Ежедневная, 3 - Еженедельная, 4 - Ежемесячная, 5 - Ежегодная");
            int appearsIn = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm");
            scanner.nextLine();
            createEvent(scanner, title, description, type, appearsIn);
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeTask (Scanner scanner) {
        System.out.println("Введите id задачи");
        int id = scanner.nextInt();
        Task t = findTasksById(id);
        if (taskSet.contains(t)) {
            taskSet.remove(t);
            System.out.println(id + " - задача удалена\n");
        } else {
            throw new TaskNotFoundException("Такой задачи не существует\n");
        }
    }

    public static void getAllByDate(Scanner scanner) {
        System.out.println("Введите дату в формате: dd.MM.yyyy");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List <Task> foundEvents = findTasksByDate(requestedDate);
            System.out.println("Задачи на " + requestedDate + " : ");
            for (Task task : foundEvents) {
                System.out.println(task);
            }
        }catch (DateTimeParseException e) {
            System.out.println("Попробуйте еще раз");
        }
        scanner.nextLine();
    }

    private static Task findTasksById (int id) {
        for (Task task : taskSet) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private static List <Task> findTasksByDate (LocalDate date) {
        List <Task> tasks = new ArrayList<>();
        for (Task task : taskSet) {
            if (task.appearsIn(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static void createEvent (Scanner scanner, String title, String description, Type taskType, int appearsIn) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse (scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task;
            try {
                task = createTask(appearsIn, title, description, taskType, eventDate);
                System.out.println("Задача сохранена " + task);
            }catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Попробуйте ввести дату еще раз");
            createEvent(scanner, title, description, taskType, appearsIn);
        }
    }

    private static Task createTask(int appearsIn, String title, String description, Type taskType, LocalDateTime localDatetime) throws IncorrectArgumentException {
        return switch (appearsIn) {
            case 1 -> {
                OneTimeTask task = new OneTimeTask(title, description, taskType, localDatetime);
                taskSet.add(task);
                yield task;
            }
            case 2 -> {
                DailyTask task = new DailyTask(title, description, taskType, localDatetime);
                taskSet.add(task);
                yield task;
            }
            case 3 -> {
                WeeklyTask task = new WeeklyTask(title, description, taskType, localDatetime);
                taskSet.add(task);
                yield task;
            }
            case 4 -> {
                MonthlyTask task = new MonthlyTask(title, description, taskType, localDatetime);
                taskSet.add(task);
                yield task;
            }
            case 5 -> {
                YearlyTask task = new YearlyTask(title, description, taskType, localDatetime);
                taskSet.add(task);
                yield task;
            }
            default -> null;
        };
    }
}
