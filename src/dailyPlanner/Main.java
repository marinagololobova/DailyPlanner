package dailyPlanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Выберите номер меню:");
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.removeTask(scanner);
                            break;
                        case 3:
                            TaskService.getAllByDate(scanner);
                            break;
                        case 4:
                            System.out.println("Завершение программы...");
                            break label;
                    }
                } else {
                    scanner.nextInt();
                    System.out.println("Вы ввели неверное значение меню...");

                }
            }
        }
    }


    private static void printMenu() {
        System.out.println("""
                1. Добавить задачу
                2. Удалить задачу по id
                3. Получить задачи на день
                4. Выход"""
        );

    }
}