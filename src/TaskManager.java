import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<Integer, Task> tasks;
    private final Map<Integer, Epic> epics;
    private final Map<Integer, SubTask> subTasks;
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
            System.out.println("Эпик с ID " + parentEpicId + " не найден.");
            return;
        }

        SubTask subTask = new SubTask(nextTaskId++, name, description, parentEpicId, Status.NEW);
        subTasks.put(subTask.getId(), subTask);

        Epic parentEpic = epics.get(parentEpicId);
        parentEpic.addSubTask(subTask);
        System.out.println("Создана подзадача: " + subTask + " для эпика: " + parentEpic.getName());
    }

    public Task findTaskById(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            return task;
        } else {
            System.out.println("Задача с ID " + taskId + " не найдена.");
            return null;
        }
    }
    public Epic findEpicById(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            return epic;
        } else {
            System.out.println("Эпик с ID " + epicId + " не найден.");
            return null;
        }
    }

    public SubTask findSubTaskById(int subTaskId) {
        SubTask subTask = subTasks.get(subTaskId);
        if (subTask != null) {
            return subTask;
        } else {
            System.out.println("Подзадача с ID " + subTaskId + " не найдена.");
            return null;
        }
    }



    public void deleteTask(int taskId) {
        tasks.remove(taskId);
        System.out.println("Задача с ID " + taskId + " удалена.");
    }

    public void deleteEpic(int epicId) {
        epics.remove(epicId);
        System.out.println("Эпик с ID " + epicId + " удален.");
    }

    public void deleteSubTask(int subTaskId) {
        SubTask subTask = subTasks.remove(subTaskId);
        if (subTask != null) {
            Epic parentEpic = epics.get(subTask.getParentEpicId());
            if (parentEpic != null) {
                parentEpic.removeSubTask(subTaskId);
            }
            System.out.println("Подзадача с ID " + subTaskId + " удалена.");
        } else {
            System.out.println("Подзадача с ID " + subTaskId + " не найдена.");
        }
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