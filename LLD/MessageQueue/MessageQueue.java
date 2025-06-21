import java.util.concurrent.LinkedBlockingQueue;

class Message{
    long msgId;
    String msgCntent;
}
public class MessageQueue
{
    private static MessageQueue instance = null;
    private LinkedBlockingQueue<Message>queue;
    private MessageQueue()
    {
        queue = new LinkedBlockingQueue<>();
    }
    //this will make the class singleton that means there is only one way to 
    // instantiate the class that is this way there wont be any other way to instantiate 
    //the class other than this way 
    public static MessageQueue getInstance()
    {
        if(instance == null)
        {
            synchronized(MessageQueue.class)
            {
                if(instance == null)
                {
                    instance = new MessageQueue();
                }
            }
        }
        return instance;
    }
    public void put(Message message) throws InterruptedException
    {
        queue.put(message);
    }
    public Message get() throws InterruptedException
    {
        return queue.take();
    }
}
class Producer implements Runnable
{
    Integer producerId;
    String msgCntent;
    public Producer(Integer id, String msg)
    {
        this.producerId = id;
        this.msgCntent = msg;
    }
    @Override
    public void run()
    {
        Message message = new Message();
        message.msgId = producerId;
        message.msgCntent = msgCntent;
        try{
            MessageQueue msgq = MessageQueue.getInstance();
            msgq.put(message);
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Producer " + producerId + " interrupted.");
        }
    }
}
class Consumer implements Runnable{
   @Override
    public void run()
    {
        Message msg = new Message();
        try{
            MessageQueue msgq = MessageQueue.getInstance();
            msg =  msgq.get();
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted.");
        }
    }
}
class MessageQueueSystem
{
    public static void main(String args[])
    {
       Thread Producer1 = new Thread(new Producer(1, "Hello"));
        Thread Consumer1 = new Thread(new Consumer());
        Producer1.start();
        Consumer1.start();
         // Wait for producers to finish their work.
         try {
            Producer1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Allow some time for consumers to process the remaining messages.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop the consumers by interrupting their threads.
        Consumer1.interrupt();

        System.out.println("Message Queue System shutdown.");
    }
}