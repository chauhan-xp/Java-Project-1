package taskManager;

public class Task {
	private String description;
	private TaskStatus status;

	public Task(String description) {
		this.description = description;
		this.status = TaskStatus.PENDING;
	}

	public String getDescription() {
		return description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[" + status + "] " + description;
	}

}
