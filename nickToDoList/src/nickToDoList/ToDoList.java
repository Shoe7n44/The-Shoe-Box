package nickToDoList;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private String priority;
    private String dueDate;

    public Task(String description, String priority, String dueDate) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

  
    public String toString() {
        return description + " [Priority: " + priority + ", Due Date: " + dueDate + "]";
    }
}

public class ToDoList {
    private List<Task> tasks;
    private Scanner scanner;

    public ToDoList() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("To-Do List Application");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Remove Task");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addTask() {
        try {
            System.out.print("Enter the task description: ");
            String description = scanner.nextLine();

            System.out.print("Enter priority (High/Medium/Low): ");
            String priority = scanner.nextLine();

            System.out.print("Enter due date (YYYY-MM-DD): ");
            String dueDate = scanner.nextLine();

            Task task = new Task(description, priority, dueDate);
            tasks.add(task);
            System.out.println("Task added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in your to-do list.");
        } else {
            System.out.println("Your To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void removeTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        try {
            System.out.print("Enter the task number to remove: ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                System.out.print("Are you sure you want to remove this task? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();

                if ("yes".equals(confirmation)) {
                    tasks.remove(taskNumber - 1);
                    System.out.println("Task removed successfully!");
                } else {
                    System.out.println("Task removal canceled.");
                }
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the buffer
        }
    }

    public void run() {
        int choice = 0;
        do {
            showMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        viewTasks();
                        break;
                    case 3:
                        removeTask();
                        break;
                    case 4:
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        showMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        } while (choice != 4);
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.run();
    }
}