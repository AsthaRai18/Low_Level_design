package FileSystem;

public class File extends Node{
    private StringBuilder content;
    public File(String name,Directory parent)
    {
        super(name,parent);
        this.content = new StringBuilder();
    }
    public void write(String data)
    {
        content.append(data);
    }
    public String read()
    {
        return content.toString();
    }
    @Override
    public boolean isDirectory()
    {
        return false;
    }
}
