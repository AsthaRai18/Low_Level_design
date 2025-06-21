package SocialNetworkingSite;
import java.util.*;
public class FeedService{
    public void addFollower(User u, User b)
    {
       u.addFollowr(b);
    }
    //fetch feed 
    public List<Post>getTopFeed(User u)
    {
        List<User>followers = u.getFollowe();
        List<Post>ans;
        PriorityQueue<Post>pq = new PriorityQueue<>();
        for(User u1:followers)
        {
            
        }
    }
}