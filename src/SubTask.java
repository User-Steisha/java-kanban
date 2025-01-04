import java.util.Objects;

public class SubTask extends Task {
    private int parentEpicId;

    public SubTask(int id, String name, String description, int parentEpicId, Status status) {
        super(id, name, description, status);
        this.parentEpicId = parentEpicId;
    }

    public int getParentEpicId() {
        return parentEpicId;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTask)) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return parentEpicId == subTask.parentEpicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentEpicId);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() +
        ", description='" + getDescription() +
        ", status=" + getStatus() +
                ", parentEpicId=" + parentEpicId +
                '}';
    }
}