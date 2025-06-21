import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;

    public ThreadPool(int nThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Thread[nThreads];

        for (int i = 0; i < nThreads; i++) {
            System.out.println("Creating worker: " + i);
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class Worker extends Thread {
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    System.out.println(": Task executed by " + Thread.currentThread().getName());
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            pool.execute(() -> System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName()));
        }
    }
}