import java.util.*;
public class Directory {
  
    private List<File> files;
    private List<Directory> directories;
    private String name;
    private FileData fileData;
   
    public Directory(String name, FileData fileData)
    {
        this.name = name;
        this.fileData = fileData;
        files = new ArrayList<>();
        directories = new ArrayList<>();
    }
    public void addFile(File file)
    {
        files.add(file);
    }
    public void addDirectory(Directory directory)
    {
        directories.add(directory);
    }
   
}
