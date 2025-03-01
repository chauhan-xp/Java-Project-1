package taskManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {

	private String filePath;

	public TaskStorage(String filePath) {
		this.filePath = filePath;
	}

	public void saveTasks(List<Task> tasks) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (Task task : tasks) {
				writer.println(task.getDescription() + "," + task.getStatus());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Task task = new Task(parts[0]);
                    task.setStatus(TaskStatus.valueOf(parts[1]));
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, return empty list
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

}
