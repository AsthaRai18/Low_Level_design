package SocialNetworkingSite;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
public class User {
    private Integer id;
    Map<Integer,User>mp;
    private String name;
    private List<User>followers;
    private List<User>following;
    private PriorityQueue<Post>posts;
    public User(Integer id, String name,List<User>followers, List<User>following, List<Post>posts){
        this.id = id;
        this.name = name;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.posts = new PriorityQueue<>();
    }
    public User getUser(Integer id)
    {
        return mp.get(id);
    }
    public void addFollowr(User folloUser)
    {
        followers.add(folloUser);
    }
    public List<User>getFollowe()
    {
        return following;
    }
}
