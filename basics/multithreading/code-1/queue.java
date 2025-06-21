import java.util.LinkedList;

class BoundedQueue<T>
{
    private final LinkedList<T>queue = new LinkedList<>();
    private final int capacity;
    BoundedQueue(int capacity)
    {
        this.capacity = capacity;
    }
    public synchronized void equeue(T item) throws InterruptedException
    {
        while(queue.size()==capacity)
        {
            wait();
        }
        queue.addLast(item);
        notifyAll();
    }
        public synchronized T dequeue() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // Wait until an item is available
            }
            T item = queue.removeFirst();
            notifyAll(); // Notify waiting threads
            return item;
        }
    
}
public class queue {
    public static void main(String args[])
    {
        BoundedQueue<Integer> queue = new BoundedQueue<>(5);

        Runnable producer = () -> {
            for(int i = 0;i<10;i++)
            {
                try{
                queue.equeue(5);
                System.out.println("Produced: "+i);
                }catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }

            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    int item = queue.dequeue();
                    System.out.println("Consumed: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
