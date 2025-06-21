class myThread implements Runnable
{
    public void run()
    {
        for(int i = 0;i<5;i++)
        {
            
            System.out.println("hello"+Thread.currentThread().getName()+"   "+i);
        }
    }
}
public class multiThread_Part3 {
    public static void main(String args[])
    {
        Thread t1 = new Thread(new myThread());
        Thread t2 = new Thread(new myThread());
       
        t1.start();
        t2.start();
    }
}
