import java.util.Objects;

public class SubTask {
    private final int id;
    private String name;
    private String description;
    private Status status;
    private int parentEpicId;

    public SubTask(int id, String name, String description, Status status, int parentEpicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.parentEpicId = parentEpicId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTask)) return false;
        SubTask subTask = (SubTask) o;
        return id == subTask.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", name='" + name +
        ", description='" + description +
        ", status=" + status +
                '}';
    }
}