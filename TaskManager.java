package taskManager;

import java.util.List;

public class TaskManager {
	private List<Task> tasks;
	private TaskStorage storage;

	public TaskManager(String filePath) {
		this.storage = new TaskStorage(filePath);
		this.tasks = storage.loadTasks();
	}

	public void addTask(String description) {
		tasks.add(new Task(description));
		storage.saveTasks(tasks);
	}

	public List<Task> getAllTasks() {
		return tasks;
	}

	public void markTaskCompleted(int index) {
		if (index >= 0 && index < tasks.size()) {
			tasks.get(index).setStatus(TaskStatus.COMPLETED);
			storage.saveTasks(tasks);
		}
	}

}
