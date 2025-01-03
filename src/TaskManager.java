import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, SubTask> subTasks;
    private int nextTaskId = 1;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public void createTask(String name, String description) {
        Task task = new Task(nextTaskId++, name, description, Status.NEW);
        tasks.put(task.getId(), task);
        System.out.println("Создана задача: " + task);
    }

    public void createEpic(String name, String description) {
        Epic epic = new Epic(nextTaskId++, name, description);
        epics.put(epic.getId(), epic);
        System.out.println("Создана многоуровневая задача: " + epic);
    }

    public void createSubTask(String name, String description, int parentEpicId) {
        if (!epics.containsKey(parentEpicId)) {
            System.out.println("Задача с ID " + parentEpicId + " не найден.");
            return;
        }

        SubTask subTask = new SubTask(nextTaskId++, name, description, Status.NEW, parentEpicId);
        subTasks.put(subTask.getId(), subTask);

        Epic parentEpic = epics.get(parentEpicId);
        parentEpic.addSubTask(subTask);
        System.out.println("Создана подзадача: " + subTask + " для эпика: " + parentEpic.getName());
    }

    public void displayTasks() {
        System.out.println("Задачи:");
        for (Task task : tasks.values()) {
            System.out.println(task);
        }

        System.out.println("Многоуровневая задача:");
        for (Epic epic : epics.values()) {
            System.out.println(epic);
            for (SubTask subTask : epic.getSubTasks()) {
                System.out.println("  - " + subTask);
            }
        }

        System.out.println("Подзадачи:");
        for (SubTask subTask : subTasks.values()) {
            System.out.println(subTask);
        }
    }
}