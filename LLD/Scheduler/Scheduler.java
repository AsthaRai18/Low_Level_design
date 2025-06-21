import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
public class Scheduler {
    private BlockingQueue<Task> queue;
    private List<Thread> workers;
    public Scheduler(int n)
    {
        queue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();
        for(int i = 0; i < n; i++)
        {
            Worker worker = new Worker(queue);
            Thread thread = new Thread(worker, "Worker-" + i);
            workers.add(thread);
            thread.start();

        }
    }
    public void submitTask(Task task) throws InterruptedException
    {
        queue.put(task);
    }
    public void stop()
    {
        for (Thread thread : workers) {
            thread.interrupt();
        }
    }
}

class Worker implements Runnable{
    private ConcurrentHashMap<String, Task> taskMap;
    private BlockingQueue<Task> queue;
    public Worker(BlockingQueue<Task> queue)
    {
        this.queue = queue;
        taskMap = new ConcurrentHashMap<>();
    }
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Task task = queue.take();
                task.execute();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
