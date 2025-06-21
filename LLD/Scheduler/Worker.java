import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Worker extends Thread{
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
