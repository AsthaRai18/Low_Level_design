class myThread extends Thread
{
    // public void run()
    // {
    //     System.out.println("Thread is running ");
    // }
}
public class part1
{
    public static void main(String args[])
    {
        Thread t1 = new myThread();
        t1.start();
    }
}