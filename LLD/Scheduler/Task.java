public class Task {
    private String taskId;
    private Runnable task;
    public Task(String taskId, Runnable task)
    {
        this.taskId = taskId;
        this.task = task;
    }
    public String getTaskId()
    {
        return taskId;
    }
    public Runnable getTask()
    {
        return task;
    }
    public void execute()
    {
        task.run();
    }
}
