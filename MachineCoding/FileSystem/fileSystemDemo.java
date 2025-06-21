package FileSystem;

public class fileSystemDemo {
    public static void Demo(String []args)
    {
        fileSystem fs = fileSystem.getInstance();
        Directory dir1 = fs.createDirectory("/", "dir1");
        Directory dir2 = fs.createDirectory("/dir1", "dir2");
        File file1 = fs.createFile("/dir1/dir2", "file1.txt");
        file1.write("Hello, FileSystem!");

    }
}
