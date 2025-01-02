import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks;

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        updateStatus();
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    private void updateStatus() {
        if (subTasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() != Status.DONE) {
                allDone = false;
            }
            if (subTask.getStatus() == Status.IN_PROGRESS) {
                anyInProgress = true;
            }
        }

        if (allDone) {
            setStatus(Status.DONE);
        } else if (anyInProgress) {
            setStatus(Status.IN_PROGRESS);
        } else {
            setStatus(Status.NEW);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +


                "id=" + getId() +
                ", name='" + getName() +
        ", description='" + getDescription() +
         ", status=" + getStatus() +
                '}';
    }
}