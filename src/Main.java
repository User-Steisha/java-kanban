import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("1 - Создать задачу");
            System.out.println("2 - Создать многоуровневую задачу");
            System.out.println("3 - Создать подзадачу");
            System.out.println("4 - Удалить задачу");
            System.out.println("5 - Удалить эпик");
            System.out.println("6 - Удалить подзадачу");
            System.out.println("7 - Показать все задачи");
            System.out.println("8 - Найти задачу по ID");
            System.out.println("9 - Найти  многоуровневую задачу по ID");
            System.out.println("10 - Найти подзадачу по ID");
            System.out.println("0 - Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Введите название задачи: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Введите описание задачи: ");
                    String taskDescription = scanner.nextLine();

                    Task newTask = new Task(taskManager.nextTaskId++, taskName, taskDescription, Status.NEW);
                    taskManager.createTask(newTask);
                    break;

                case 2:
                    System.out.print("Введите название многоуровневой задачи: ");
                    String epicName = scanner.nextLine();
                    System.out.print("Введите описание многоуровневой задачи: ");
                    String epicDescription = scanner.nextLine();

                    Epic newEpic = new Epic(taskManager.nextTaskId++, epicName, epicDescription);
                    taskManager.createEpic(newEpic);
                    break;

                case 3:
                    System.out.print("Введите ID эпика: ");
                    int parentId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Введите название подзадачи: ");
                    String subTaskName = scanner.nextLine();
                    System.out.print("Введите описание подзадачи: ");
                    String subTaskDescription = scanner.nextLine();

                    SubTask newSubTask = new SubTask(taskManager.nextTaskId++, subTaskName, subTaskDescription, parentId, Status.NEW);
                    taskManager.createSubTask(newSubTask);
                    break;


                case 4:
                    System.out.print("Введите ID задачи для удаления: ");
                    int taskIdToDelete = Integer.parseInt(scanner.nextLine());
                    taskManager.deleteTask(taskIdToDelete);
                    break;

                case 5:
                    System.out.print("Введите ID многоуровневой задачи для удаления: ");
                    int epicIdToDelete = Integer.parseInt(scanner.nextLine());
                    taskManager.deleteEpic(epicIdToDelete);
                    break;

                case 6:
                    System.out.print("Введите ID подзадачи для удаления: ");
                    int subTaskIdToDelete = Integer.parseInt(scanner.nextLine());
                    taskManager.deleteSubTask(subTaskIdToDelete);
                    break;


                case 7:
                    displayTasks(taskManager);
                    break;

                case 8:
                    System.out.println("Введите ID задачи для поиска: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    Task foundTask = taskManager.findTaskById(taskId);
                    if (foundTask != null) {
                        System.out.println("Найдена задача: " + foundTask);
                    }
                    break;

                case 9:
                    System.out.println("Введите ID многоуровневой задачи для поиска: ");
                    int epicId = Integer.parseInt(scanner.nextLine());
                    Epic foundEpic = taskManager.findEpicById(epicId);
                    if (foundEpic != null) {
                        System.out.println("Найдена задача: " + foundEpic);
                    }
                    break;

                case 10:
                    System.out.println("Введите ID подзадачи для поиска: ");
                    int subTaskId = Integer.parseInt(scanner.nextLine());
                    SubTask foundSubTask = taskManager.findSubTaskById(subTaskId);
                    if (foundSubTask != null) {
                        System.out.println("Найдена задача: " + foundSubTask);
                    }
                    break;

                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
    private static void displayTasks (TaskManager taskManager){
        System.out.println("Задачи:");
        List<Task> tasks = taskManager.getAllTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }

        System.out.println("Многоуровневые задачи:");
        List<Epic> epics = taskManager.getAllEpics();
        for (Epic epic : epics) {
            System.out.println(epic);
            List<SubTask> subTasks = epic.getSubTasks();
            for (SubTask subTask : subTasks) {
                System.out.println("  - " + subTask);
            }
        }

        System.out.println("Подзадачи:");
        List<SubTask> subTasks = taskManager.getAllSubTasks();
        for (SubTask subTask : subTasks) {
            System.out.println(subTask);
        }

    }
}