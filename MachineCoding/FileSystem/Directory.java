package FileSystem;

import java.util.HashMap;
import java.util.*;
public class Directory extends Node{
    private Map<String,Node>mp;
    public Directory(String name, Directory parent)
    {
        super(name, parent);
        mp = new HashMap<>();
    }
    public void addNode(Node node)
    {
        mp.put(node.getName(),node);
    }
    public void removeNode(String name)
    {
        mp.remove(name);
    }
    public Node getNode(String name)
    {
        return mp.getName(name);
    }
    @Override
    public boolean isDirectory()
    {
        return true;
    }
}
