package ProjectToDo;

public class Task {
    private String taskID;
    private String taskTitle;
    private String taskDecription;
    private String taskDueDate;
    private Boolean taskStatus;

    public Task(String taskID, String taskTitle, String taskDecription, String taskDueDate, Boolean taskStatus) {
        setTaskID(taskID);
        setTaskTitle(taskTitle);
        setTaskDecription(taskDecription);
        setTaskDueDate(taskDueDate);
        setTaskStatus(taskStatus);
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDecription() {
        return taskDecription;
    }

    public void setTaskDecription(String taskDecription) {
        this.taskDecription = taskDecription;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

}
