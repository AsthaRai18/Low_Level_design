package SocialNetworkingSite;

import java.security.Timestamp;
import java.sql.Time;

public class Post implements Comparable<Post>{
    private int id;
    private long timeStamp;
    private String img;
    private String description;
    @Override
    public int compareTo(Post other) {
        // Compare posts based on their timeStamp
        return Long.compare(this.timeStamp, other.timeStamp);
    }

}
