package FileSystem;
import java.util.*;
public abstract class Node {
    private String name;
    private Directory parent;
    private Date creationTime;
    public Node(String NodeName,Directory parent1)
    {
        name = NodeName;
        parent = parent1;
        creationTime = new Date();
    }
    public String getName()
    {
        return name;
    }
    public String getPath()
    {
        if(parent == null)
        {
            return name;
        }
        return parent.getPath()+"/"+name;
    }
    public Date getCreationTime()
    {
        return creationTime;
    }
    public abstract boolean isDirectory();
}
