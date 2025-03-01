package taskManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.JTextField;

public class TaskManagerApp {
	private static TaskManager taskManager;
	private static JTextArea taskTextArea;
	private static JTextField taskTextField;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		taskManager = new TaskManager("tasks.txt");

		JFrame frame = new JFrame("Task Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		taskTextField = new JTextField(20);
		JButton addButton = new JButton("Add Task");
		inputPanel.add(taskTextField);
		inputPanel.add(addButton);

		taskTextArea = new JTextArea();
		taskTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(taskTextArea);

		JPanel buttonPanel = new JPanel();
		JButton completeButton = new JButton("Mark Completed");
		buttonPanel.add(completeButton);

		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String description = taskTextField.getText();
				if (!description.isEmpty()) {
					taskManager.addTask(description);
					taskTextField.setText("");
					updateTaskList();
				}
			}
		});

		completeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(frame, "Enter task number to mark completed:");
				if (input != null && !input.isEmpty()) {
					try {
						int taskNumber = Integer.parseInt(input);
						taskManager.markTaskCompleted(taskNumber - 1);
						updateTaskList();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid task number.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		updateTaskList();
		frame.setVisible(true);
	}

	private static void updateTaskList() {
		taskTextArea.setText("");
		List<Task> tasks = taskManager.getAllTasks();
		for (int i = 0; i < tasks.size(); i++) {
			taskTextArea.append((i + 1) + ". " + tasks.get(i) + "\n");
		}
	}

}
/*
 * public static void main(String[] args) {
        TaskManager taskManager = new TaskManager("tasks.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(description);
                    break;
                case 2:
                    List<Task> tasks = taskManager.getAllTasks();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Enter task number to mark completed: ");
                    int taskNumber = scanner.nextInt();
                    taskManager.markTaskCompleted(taskNumber - 1);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }*/
