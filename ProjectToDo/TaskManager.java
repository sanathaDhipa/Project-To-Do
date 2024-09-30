package ProjectToDo;

import java.util.*;

public class TaskManager {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int number = 1;

    public void addTask() {
        boolean task = false;
        do {
            clearScreen();

            // Task Title
            String taskTitle;
            do {
                System.out.print("Task title[0 - 20 char]['-' for cancel]: ");
                taskTitle = scanner.nextLine();
            } while (taskTitle.length() > 20 || taskTitle.length() == 0);
            if (taskTitle.equals("-")) {
                task = true;
                continue;
            }
            String subTitle = taskTitle.substring(0, 3);
            String taskID = String.format("%s%03d", subTitle, number);

            // Task Description
            String taskDescription;
            do {
                System.out.print("Task description[0 - 100 char]['-' for cancel]: ");
                taskDescription = scanner.nextLine();
            } while (taskDescription.length() > 100 || taskDescription.length() == 0);
            if (taskDescription.equals("-")) {
                task = true;
                continue;
            }

            // Task DueDate
            String dueDate;
            boolean dueDateValid = false;
            do {
                System.out.print("Set Due Date[day/month/year]['-' for cancel]: ");
                dueDate = scanner.nextLine();
                if (dueDate.equals("-")) {
                    dueDateValid = true;
                    continue;
                } else {
                    dueDateValid = dateValidation(dueDate);
                }
            } while (!dueDateValid);
            if (dueDate.equals("-")) {
                task = true;
                continue;
            }

            // Task Input
            Task taskTemp = new Task(taskID, taskTitle, taskDescription, dueDate, false);
            taskList.add(taskTemp);
            number++;
            String anoChoice;
            do {
                System.out.print("Add another task[yes | no]: ");
                anoChoice = scanner.nextLine();
            } while (!anoChoice.equalsIgnoreCase("yes") && !anoChoice.equalsIgnoreCase("no"));
            if (anoChoice.equalsIgnoreCase("no")) {
                task = true;
            }
        } while (!task);
        System.out.print("Press any key to continue. . .");
        scanner.nextLine();
    }

    private boolean dateValidation(String date) {
        String[] dateParts = date.split("/");
        int day = 0, month = 0, year = 0;
        try {
            if (dateParts.length != 3) {
                throw new InputMismatchException("Invalid date format[day/month/year]");
            }
            day = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
            if (day > 31 || day < 1 || month > 12 || month < 1 || year < 2023 || year > 2100) {
                throw new InputMismatchException("Invalid date[1 - 31][1 - 12][>=2023]");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void displayTask() {
        display();
        System.out.print("Press any key to continue. . .");
        scanner.nextLine();
    }

    public void removeTask() {
        if (taskList.size() == 0) {
            display();
            System.out.println("List is empty");
        } else {
            boolean remove = false;
            do {
                clearScreen();
                display();
                String targetRemove;
                boolean valid;
                do {
                    System.out.print("Task to be removed[based on ID]['-' for cancel]: ");
                    targetRemove = scanner.nextLine();
                    if (targetRemove.equals("-")) {
                        valid = true;
                        continue;
                    } else {
                        valid = isTask(targetRemove);
                    }
                } while (targetRemove.length() > 6 || targetRemove.length() < 1 || !valid);
                if (targetRemove.equals("-")) {
                    remove = true;
                    continue;
                }

                String choice;
                do {
                    System.out.printf("Task %s delete confirmation[yes | no]: ", targetRemove);
                    choice = scanner.nextLine();
                } while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no"));
                if (choice.equals("yes")) {
                    for (int i = 0; i < taskList.size(); i++) {
                        Task taskTemp = taskList.get(i);
                        if (taskTemp.getTaskID().equals(targetRemove)) {
                            taskList.remove(i);
                        }
                    }
                }
                if (taskList.size() != 0) {
                    String anoChoice;
                    do {
                        System.out.print("Remove another task[yes | no]: ");
                        anoChoice = scanner.nextLine();
                    } while (!anoChoice.equalsIgnoreCase("yes") && !anoChoice.equalsIgnoreCase("no"));
                    if (anoChoice.equalsIgnoreCase("no")) {
                        remove = true;
                    }
                } else {
                    remove = true;
                }
            } while (!remove);
        }
        System.out.print("Press any key to continue. . .");
        scanner.nextLine();
    }

    public void editTask() {
        if (taskList.size() == 0) {
            display();
            System.out.println("List is empty");
        } else {
            boolean edit = false;
            do {
                clearScreen();
                display();
                String targetEdit;
                boolean valid;
                do {
                    System.out.print("Task to be changed[based on ID]['-' for cancel]: ");
                    targetEdit = scanner.nextLine();
                    if (targetEdit.equals("-")) {
                        valid = true;
                        continue;
                    } else {
                        valid = isTask(targetEdit);
                    }
                } while (targetEdit.length() > 6 || targetEdit.length() < 1 || !valid);
                if (targetEdit.equals("-")) {
                    edit = true;
                    continue;
                }

                clearScreen();
                displayOneTask(targetEdit);
                for (int i = 0; i < taskList.size(); i++) {
                    Task taskTemp = taskList.get(i);
                    if (taskTemp.getTaskID().equals(targetEdit)) {
                        // Task Title
                        String taskTitleChange;
                        do {
                            System.out.print("Task title[0 - 20 char]['-' for cancel]: ");
                            taskTitleChange = scanner.nextLine();
                        } while (taskTitleChange.length() > 20 || taskTitleChange.length() == 0);
                        if (taskTitleChange.equals("-")) {
                            edit = true;
                            continue;
                        }

                        // Task Description
                        String taskDescriptionChange;
                        do {
                            System.out.print("Task description[0 - 100 char]['-' for cancel]: ");
                            taskDescriptionChange = scanner.nextLine();
                        } while (taskDescriptionChange.length() > 100 || taskDescriptionChange.length() == 0);
                        if (taskDescriptionChange.equals("-")) {
                            edit = true;
                            continue;
                        }

                        // DUEDATE
                        String dueDateChange;
                        boolean dueDateValid = false;
                        do {
                            System.out.print("Set Due Date[day/month/year]['-' for cancel]: ");
                            dueDateChange = scanner.nextLine();
                            if (dueDateChange.equals("-")) {
                                dueDateValid = true;
                                continue;
                            } else {
                                dueDateValid = dateValidation(dueDateChange);
                            }
                        } while (!dueDateValid);
                        if (dueDateChange.equals("-")) {
                            edit = true;
                            continue;
                        }

                        String confirmation;
                        do {
                            System.out.print("Confirm changes[yes | no]: ");
                            confirmation = scanner.nextLine();
                        } while (!confirmation.equalsIgnoreCase("yes") && !confirmation.equalsIgnoreCase("no"));
                        if (confirmation.equals("yes")) {
                            taskList.get(i).setTaskTitle(taskTitleChange);
                            taskList.get(i).setTaskDecription(taskDescriptionChange);
                            taskList.get(i).setTaskDueDate(dueDateChange);
                        }
                    }
                }
                if (taskList.size() != 0) {
                    String anoChoice;
                    do {
                        System.out.print("Edit another task[yes | no]: ");
                        anoChoice = scanner.nextLine();
                    } while (!anoChoice.equalsIgnoreCase("yes") && !anoChoice.equalsIgnoreCase("no"));
                    if (anoChoice.equalsIgnoreCase("no")) {
                        edit = true;
                    }
                } else {
                    edit = true;
                }
            } while (!edit);
        }
        System.out.print("Press any key to continue. . .");
        scanner.nextLine();
    }

    public void updateTaskStatus() {
        if (taskList.size() == 0) {
            display();
            System.out.println("List is empty");
        } else {
            boolean update = false;
            do {
                clearScreen();
                display();
                String targetUpdate;
                boolean valid;
                do {
                    System.out.print("Task to be updated[based on ID]['-' for cancel]: ");
                    targetUpdate = scanner.nextLine();
                    if (targetUpdate.equals("-")) {
                        valid = true;
                    } else {
                        valid = isTask(targetUpdate);
                    }
                } while (targetUpdate.length() > 6 || targetUpdate.length() < 1 || !valid);
                if (targetUpdate.equals("-")) {
                    update = true;
                    continue;
                }

                clearScreen();
                displayOneTask(targetUpdate);
                String status;
                do {
                    System.out.print("Set Task status[Completed | In Progress]['-' for cancel]: ");
                    status = scanner.nextLine();
                } while (!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("In Progress")
                        && !status.equals("-"));
                if (status.equals("-")) {
                    update = true;
                    continue;
                }
                for (int i = 0; i < taskList.size(); i++) {
                    Task taskTemp = taskList.get(i);
                    if (taskTemp.getTaskID().equals(targetUpdate)) {
                        if (status.equalsIgnoreCase("Completed")) {
                            taskTemp.setTaskStatus(true);
                        } else {
                            taskTemp.setTaskStatus(false);
                        }
                    }
                }

                if (taskList.size() != 0 && taskList.size() != 1) {
                    String anoChoice;
                    do {
                        System.out.print("Update another task status[yes | no]: ");
                        anoChoice = scanner.nextLine();
                    } while (!anoChoice.equalsIgnoreCase("yes") && !anoChoice.equalsIgnoreCase("no"));
                    if (anoChoice.equalsIgnoreCase("no")) {
                        update = true;
                    }
                } else {
                    update = true;
                }
            } while (!update);
        }

        System.out.print("Press any key to continue. . .");
        scanner.nextLine();
    }

    private void display() {
        if (taskList.size() == 0) {
            System.out.println(
                    "=========================================== ToDo - List App ===============================================");
            System.out.println(
                    "| Task ID |         Title         |          Description          |       Due Date       |     Status     |");
            System.out.println(
                    "|   ---   |          ---          |              ---              |          ---         |       --       |");
            System.out.println(
                    "===========================================================================================================");
        } else {
            System.out.println(
                    "=========================================== ToDo - List App ===============================================");
            System.out.println(
                    "| Task ID |         Title         |          Description          |       Due Date       |     Status     |");
            for (int i = 0; i < taskList.size(); i++) {
                Task taskTemp = taskList.get(i);
                String status;
                if (taskTemp.getTaskStatus()) {
                    status = "Completed";
                } else {
                    status = "In Progress";
                }
                System.out.printf("|%9s|%23s|%31s|%22s|%16s|\n", taskTemp.getTaskID(), taskTemp.getTaskTitle(),
                        taskTemp.getTaskDecription(), taskTemp.getTaskDueDate(), status);
            }
            System.out.println(
                    "===========================================================================================================");
        }
    }

    private void displayOneTask(String taskSearch) {
        System.out.println(
                "=========================================== ToDo - List App ===============================================");
        System.out.println(
                "| Task ID |         Title         |          Description          |       Due Date       |     Status     |");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID().equals(taskSearch)) {
                Task taskTemp = taskList.get(i);
                String status;
                if (taskTemp.getTaskStatus()) {
                    status = "Completed";
                } else {
                    status = "In Progress";
                }
                System.out.printf("|%9s|%23s|%31s|%22s|%16s|\n", taskTemp.getTaskID(), taskTemp.getTaskTitle(),
                        taskTemp.getTaskDecription(), taskTemp.getTaskDueDate(), status);
            }
        }
        System.out.println(
                "===========================================================================================================");
    }

    private boolean isTask(String taskID) {
        for (int i = 0; i < taskList.size(); i++) {
            Task taskTemp = taskList.get(i);
            if (taskTemp.getTaskID().equals(taskID)) {
                return true;
            }
        }
        return false;
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            System.out.println("Clear Screen Error!");
        }
    }
}
