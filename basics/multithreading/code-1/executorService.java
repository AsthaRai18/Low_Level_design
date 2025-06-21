import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class executorService {
    public static void main(String args[])
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0;i<5;i++)
        {
            int taskid = i;
            executor.submit(() -> {
                System.out.println("Task " + taskid + " executed by " + Thread.currentThread().getName());
            });          
        }       
        executor.shutdown();
    }
}
