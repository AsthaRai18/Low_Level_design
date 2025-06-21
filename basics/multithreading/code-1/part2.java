class myThread implements Runnable
{
    public void run()
    {
        System.out.println("Thread is running.");
    }
}
public class part2 {
    public static void main(String args[])
    {
        Thread t1 = new Thread(new myThread());
        t1.start();
    }
}
