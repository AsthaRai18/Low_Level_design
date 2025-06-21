package Twitter;
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
    // Twitter twitter;
    // twitter.addUser(1);
    // twitter.addUser(2);
    
    // User* user1 = twitter.getUser(1);
    // User* user2 = twitter.getUser(2);
    
    // user1->postTweet(101);
    // user2->postTweet(201);

    // user1->getNewsFeed();   // It should print 101

    // user1->follow(user2);
    // user1->getNewsFeed();  // It should print 101, 102
import java.util.*;
class User
{
    private Integer userId;
    private Set<User>following;
    // private Set<User>following;
    private List<Tweet>tweets;
    public User(Integer id)
    {
        userId = id;
        following = new HashSet<>();
        tweets = new ArrayList<>();
    }
    public void postTweet(int id)
    {
        Tweet tweet = new Tweet(id);
        tweets.add(tweet);
    }
    public List<Tweet>getTweets()
    {
        return tweets;
    }
    public void follow(User u)
    {
        following.add(u);
    }
    public void unfollow(User u)
    {
        following.remove(u);
    }
    public Integer getId()
    {
        return userId;
    }
    public List<Tweet> getNewsFeed()
    {
        List<Tweet>tweetsList = new ArrayList<>();
        PriorityQueue<Tweet>pq = new PriorityQueue<>();
        for(Tweet t:tweets)
        {
            pq.offer(t);
        }
        for(User u:following)
        {
            for(Tweet t:u.getTweets())
            {
                pq.offer(t);
            }
        }
        int numTweets = 10;
        while(!pq.isEmpty() && numTweets>0)
        {
            tweetsList.add(pq.poll());
            numTweets = numTweets-1;
        }
        return tweetsList;
    }
}
class Tweet implements Comparable<Tweet>
{
    private Integer tweetId;
    private static Integer timestamp= 0;
    // private User user;
    public Tweet(int id)
    {
        this.tweetId = id;
        this.timestamp++;
    }
    public Integer getTweetId()
    {
        return tweetId;
    }
    @Override
    public int compareTo(Tweet t)
    {
        return Integer.compare(t.timestamp,this.timestamp);
    }
}
class Twitter
{
    Map<Integer,User>userMap;
    public Twitter()
    {
        userMap = new HashMap<>();
    }
    public void addUser(Integer id)
    {
        User u = new User(id);
        userMap.put(id,u);
    }
    public User getUser(Integer id)
    {
        return userMap.get(id);
    }
}
class Test 
{
    private Integer id;
    private Long num;
    public Integer getid()
    {
        return id;
    }
}
class Tes
{
    public void checking()
    {
    Test t11 = new Test();
    List<Test>testList = new ArrayList<>();
    testList.add(t11);
    Collections.sort(testList, (t1,t2)->Integer.compare(t1.getid(), t2.getid()));
    List<Integer>list1 = new ArrayList<>(Arrays.asList(1,2,3));
    Collections.sort(list1,Collections.reverseOrder());
    Map<String, Integer>map = new HashMap<>();
    Iterator<Map.Entry<String,Integer>>mp = map.entrySet().iterator();
    while(mp.hasNext())
    {
        Map.Entry<String,Integer>entry = mp.next();
    }

    for(Map.Entry<String,Integer>mp :map.entrySet())
    {

    }
    PriorityQueue<Integer>pq = new PriorityQueue<>();
    Set<Integer>st = new HashSet<>();
    st.add(1);
    Iterator<Integer>it = st.iterator();

    }
}


class Main {
    static void printNewsFeed(List<Tweet>tweetsList)
    {
        for(Tweet t:tweetsList)
        {
            System.out.println(t.getTweetId()+" ");
        }
    }
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.addUser(1);
        twitter.addUser(2);
        
        User user1 = twitter.getUser(1);
        User user2 = twitter.getUser(2);
        
        user1.postTweet(101);
        user2.postTweet(201);
    
        List<Tweet> tweetsList= user1.getNewsFeed();   // It should print 101
       
        printNewsFeed(tweetsList);
        user1.follow(user2);
        List<Tweet> tweetsList1=  user1.getNewsFeed();
        printNewsFeed(tweetsList1);
        user1.unfollow(user2);
        printNewsFeed(user1.getNewsFeed());
        // It should print 101, 102
    }
} 
