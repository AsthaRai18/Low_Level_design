package FileSystem;

public class fileSystem {
    private static volatile fileSystem instance;
    private Directory root;
    private fileSystem()
    {
        root = new Directory("root", root);
    }
    public static fileSystem getInstance()
    {
        if(instance == null)
        {
            instance = new fileSystem();
        }
        return instance;
    }
    public Directory getRoot()
    {
        return root;
    }
    public File createFile(String path,String fileName)
    {
        Directory dir = traversePath(path);
        if (dir == null) {
            throw new IllegalArgumentException("Invalid directory path: " + path);
        }
        File file = new File(fileName, dir);
        dir.addNode(file);
        return file;

    }
    public Directory createDirectory(String path, String dirName)
    {
        Directory dir = traversePath(path);
        if (dir == null) {
            throw new IllegalArgumentException("Invalid directory path: " + path);
        }
        Directory newDir = new Directory(dirName, dir);
        dir.addNode(newDir);
        return newDir;
    }
    private Directory traversePath(String path) {
        if (path.equals("/") || path.equals("root") || path.equals("/root"))
            return root;
        String[] parts = path.split("/");
        Directory current = root;
        for (String part : parts) {
            if (part.isEmpty() || part.equals("root"))
                continue;
            Node node = current.getNode(part);
            if (node == null || !node.isDirectory()) {
                return null;  // Invalid path
            }
            current = (Directory) node;
        }
        return current;
    }

}
