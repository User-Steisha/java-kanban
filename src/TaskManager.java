import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private final Map<Integer, Task> tasks;
    private final Map<Integer, Epic> epics;
    private final Map<Integer, SubTask> subTasks;
    int nextTaskId = 1;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public void createTask(Task task) {
        if (task != null) {
            tasks.put(task.getId(), task);
            System.out.println("Создана задача: " + task);
        } else {
            System.out.println("Ошибка: задача не может быть пустой");
        }
    }

    public void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        System.out.println("Создана многоуровневая задача: " + epic);
    }

    public void createSubTask(SubTask subTask) {
        int parentEpicId = subTask.getParentEpicId();

        if (!epics.containsKey(parentEpicId)) {
            System.out.println("Многоуровневая задача с ID " + parentEpicId + " не найдена.");
            return;
        }
        subTasks.put(subTask.getId(), subTask);
        Epic parentEpic = epics.get(parentEpicId);
        parentEpic.addSubTask(subTask);
        System.out.println("Создана подзадача: " + subTask + " для многоуровневой задачи: " + parentEpic.getName());
    }
    public void updateTask(Task newTask) {
        if (tasks.containsKey(newTask.getId())) {
            tasks.put(newTask.getId(), newTask);
            System.out.println("Задача обновлена: " + newTask);
        } else {
            System.out.println("Задача с ID " + newTask.getId() + " не найдена.");
        }
    }

    public void updateEpic(Epic newEpic) {
        if (epics.containsKey(newEpic.getId())) {
            epics.put(newEpic.getId(), newEpic);
            System.out.println("Многоуровневая задача обновлена: " + newEpic);
        } else {
            System.out.println("Эпик с ID " + newEpic.getId() + " не найден.");
        }
    }

    public void updateSubTask(SubTask newSubTask) {
        if (subTasks.containsKey(newSubTask.getId())) {
            subTasks.put(newSubTask.getId(), newSubTask);
            System.out.println("Подзадача обновлена: " + newSubTask);
        } else {
            System.out.println("Подзадача с ID " + newSubTask.getId() + " не найдена.");
        }
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
    public void deleteAllTasks() {
        tasks.clear();
        System.out.println("Все задачи удалены.");
    }

    public void deleteEpic(int epicId) {
        Epic epicToDelete = epics.remove(epicId);
        if (epicToDelete != null) {
            for (SubTask subTask : epicToDelete.getSubTasks()) {
                subTasks.remove(subTask.getId());
            }
            epicToDelete.setStatus(Status.DONE);
            System.out.println("Эпик с ID " + epicId + " и все связанные подзадачи удалены.");
        } else {
            System.out.println("Эпик с ID " + epicId + " не найден.");
        }
    }
    public void deleteAllEpics() {
        for (Epic epic : epics.values()) {
            for (SubTask subTask : epic.getSubTasks()) {
                subTasks.remove(subTask.getId());
            }
        }
        epics.clear();
        System.out.println("Все эпики и связанные подзадачи удалены.");
    }

    public void deleteSubTask(int subTaskId) {
        SubTask subTask = subTasks.remove(subTaskId);
        if (subTask != null) {
            Epic parentEpic = epics.get(subTask.getParentEpicId());
            if (parentEpic != null) {
                parentEpic.removeSubTask(subTaskId);
                if (!parentEpic.getSubTasks().isEmpty()) {
                    parentEpic.setStatus(Status.IN_PROGRESS);
                } else {
                    parentEpic.setStatus(Status.DONE);
                }
                System.out.println("Статус эпической задачи с ID " + parentEpic.getId() + " обновлен.");
            }
            System.out.println("Подзадача с ID " + subTaskId + " удалена.");
        } else {
            System.out.println("Подзадача с ID " + subTaskId + " не найдена.");
        }
    }
    public void deleteAllSubTasks(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            System.out.println("Эпик с ID " + epicId + " не найден.");
            return;
        }

        for (SubTask subTask : new ArrayList<>(epic.getSubTasks())) {
            deleteSubTask(subTask.getId());
        }
        if (epic.getSubTasks().isEmpty()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }

        System.out.println("Все подзадачи для эпика с ID " + epicId + " удалены. Статус эпика обновлен.");
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }
    public List<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }
}