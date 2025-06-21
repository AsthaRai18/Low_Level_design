public class FileData {
    private String name;
    boolean isDirectory;
    private long size;
    private long createdTime;
    private long modifiedTime;
    public FileData(String name, boolean isDirectory, long size, long createdTime, long modifiedTime)
    {
        this.name = name;
        this.isDirectory = isDirectory;
        this.size = size;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }
    public String getName()
    {
        return name;
    }
    public boolean isDirectory()
    {
        return isDirectory;
    }
    public long getSize()
    {
        return size;
    }
    public long getCreatedTime()
    {
        return createdTime;
    }
    public long getModifiedTime()
    {
        return modifiedTime;
    }
}
