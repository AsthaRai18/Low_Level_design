package KV_Store;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.print.attribute.PrintRequestAttribute;

public class Map1<K,V>{
    ConcurrentHashMap<Entry,Integer>map = new ConcurrentHashMap<>();
    PriorityQueue<Entry>pq = new PriorityQueue<>();
    private Thread t;
    public Map1()
    {
        t = new Thread(()->
        {
            while(true)
            {
                cleanup();
                try
                {
                    Thread.sleep(100);
                }catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        t.setDaemon(true);
        t.start();
        
    }
    public static class Entry
    {
        int key;
        long time;
        Entry(int key, long time)
        {
            this.key = key;
            this.time = time;
        }
    }
    public void cleanUp() 
    {
        while(!pq.isEmpty() && pq.peek().time<System.currentTimeMillis())
        {
            map.remove(pq.peek());
            pq.poll();
        }
    }
    public void put(int key, int value,long time)
    {
        Entry entry = new Entry(key,time);
        map.put(entry, value);
        pq.add(entry);
    } 
}
class Pair<K,V>
{
    private K key;
    private V value;
    public Pair()
    {
    }
    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
    public K getKey()
    {
        return key;
    }
    public V getValue()
    {
        return value;
    }
}
class InMemory {
    public static void main(String args[])
    {
        Pair<Integer,Integer>p = new Pair<>();
        PriorityQueue<Integer>maxHeap = new PriorityQueue<>(Collections.reverseOrder());
         {
            String fileName = "sample.txt";
    
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));                writer.write("Hello, this is a test file!");
                writer.newLine(); // Adds a new line
                writer.write("BufferedWriter is efficient for writing text files.");
                System.out.println("File written successfully.");
           
    
        Map1<Integer,Long> map = new Map1<>();
        map.put(1, 1, System.currentTimeMillis()+1000);
        map.put(2, 2, System.currentTimeMillis()+2000);
    }
}
