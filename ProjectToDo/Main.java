package ProjectToDo;

import java.util.*;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    public TaskManager toDoList = new TaskManager();

    public Main() {
        int choice = 0;
        boolean valid = false;
        do {
            do {
                choice = 0;
                toDoList.clearScreen();
                System.out.println("----------- ToDo - List App -----------");
                System.out.println(
                        "1. Add New Task\n2. Display Task\n3. Remove Task\n4. Edit Task\n5. Update Task Status\n6. Exit");
                System.out.println("---------------------------------------");
                System.out.print(">> ");
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Invalid input. Must be integer between 1 - 6");
                    valid = false;
                    System.out.print("Press any key to continue. . .");
                    scanner.nextLine();
                }
                valid = true;
            } while (choice < 1 || choice > 6 || !valid);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    toDoList.clearScreen();
                    toDoList.addTask();
                    break;
                case 2:
                    toDoList.clearScreen();
                    toDoList.displayTask();
                    break;
                case 3:
                    toDoList.clearScreen();
                    toDoList.removeTask();
                    break;
                case 4:
                    toDoList.clearScreen();
                    toDoList.editTask();
                    break;
                case 5:
                    toDoList.clearScreen();
                    toDoList.updateTaskStatus();
                    break;
                case 6:
                    System.out.println("Exit Successfully");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (choice != 6);
        scanner.close();
    }

    public static void main(String[] args) {
        new Main();
    }
}
