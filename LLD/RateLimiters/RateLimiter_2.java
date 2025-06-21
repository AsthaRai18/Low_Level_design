// slidig window provides maximum number of equests say n in t secnds

import java.util.Deque;
import java.util.LinkedList;

class SlidingWindowRateLimiter
{
    int maxTimeRequest; 
    int timeWindowSecond;
    Deque<Long>timeStamps;
    public SlidingWindowRateLimiter(int maxTimeRequest, int timeWindowSecond)
    {
        this.maxTimeRequest = maxTimeRequest;
        this.timeWindowSecond = timeWindowSecond;
        this.timeStamps = new LinkedList<>();
    }
    public synchronized Boolean request()
    {
        long now = System.currentTimeMillis();
        long windowStart = now - timeWindowSecond*1000L;
        while(!timeStamps.isEmpty() && timeStamps.peek()<windowStart)
        {
            timeStamps.poll();
        }
        if(timeStamps.size()<maxTimeRequest)
        {
            timeStamps.add(now);
            return true;
        }
        return false;
    }

}
public class RateLimiter_2
{
    SlidingWindowRateLimiter s = new SlidingWindowRateLimiter(4, 5);
    
}