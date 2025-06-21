public class File {
    String fileName;
    String content;
    private FileData fileData;
    public File(String fileName, String fString, FileData fileData)
    {
        this.fileName = fileName;
        this.content = fString;
        this.fileData = fileData;
    }
}
