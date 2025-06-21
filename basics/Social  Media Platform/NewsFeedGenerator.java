import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewsFeedGenerator {
    private User user;
    private List<Post> posts;

    public NewsFeedGenerator(User user, List<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    public List<Post> generateFeed() {
        List<Post> relevantPosts = new ArrayList<>();
        Set<Integer> following = user.getProfile().getFollowing();

        // Filter posts: include posts from users the current user follows or the user’s own posts.
        for (Post post : posts) {
            if (following.contains(post.getAuthor().getUserId()) || post.getAuthor().getUserId() == user.getUserId()) {
                relevantPosts.add(post);
            }
        }

        // Sort posts by timestamp (most recent first)
        Collections.sort(relevantPosts, new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                return p2.getTimestamp().compareTo(p1.getTimestamp());
            }
        });

        return relevantPosts;
    }

}
