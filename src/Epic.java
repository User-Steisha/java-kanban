import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<SubTask> subTasks;

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        updateStatus();
    }
    public void removeSubTask(int subTaskId) {
        subTasks.removeIf(subTask -> subTask.getId() == subTaskId);
        updateStatus();
    }


    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setStatus(Status status) {
        super.setStatus(status);
    }

    private void updateStatus() {
        if (subTasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

        boolean done = true;
        boolean inProgress = false;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() != Status.DONE) {
                done = false;
            }
            if (subTask.getStatus() == Status.IN_PROGRESS) {
                inProgress = true;
            }
        }

        if (done) {
            setStatus(Status.DONE);
        } else if (inProgress) {
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