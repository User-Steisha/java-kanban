import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.print("Выберите действие: ");
            System.out.println("1 - Создать задачу");
            System.out.println("2 - Создать многоуровневую задачу");
            System.out.println("3 - Создать подзадачу");
            System.out.println("4 - Показать все задачи");
            System.out.println("0 - Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Введите название задачи: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Введите описание задачи: ");
                    String taskDescription = scanner.nextLine();
                    taskManager.createTask(taskName, taskDescription);
                    break;

                case 2:
                    System.out.print("Введите название многоуровневой задачи: ");
                    String epicName = scanner.nextLine();
                    System.out.print("Введите описание многоуровневой задачи: ");
                    String epicDescription = scanner.nextLine();
                    taskManager.createEpic(epicName, epicDescription);
                    break;

                case 3:
                    System.out.print("Введите название подзадачи: ");
                    String subTaskName = scanner.nextLine();
                    System.out.print("Введите описание подзадачи: ");


                    String subTaskDescription = scanner.nextLine();
                    System.out.print("Введите ID: ");
                    int parentId = Integer.parseInt(scanner.nextLine());
                    taskManager.createSubTask(subTaskName, subTaskDescription, parentId);
                    break;

                case 4:
                    taskManager.displayTasks();
                    break;

                case 0:
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}