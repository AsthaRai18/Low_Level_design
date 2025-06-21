import java.sql.Date;

public class FileItem {
    private String fileId;
    private String name;
    private long size;         // File size in bytes
    private Date createdAt;
    private Date modifiedAt;
    private byte[] content;    // Simulated file content

    public FileItem(String fileId, String name, long size, Date createdAt, Date modifiedAt, byte[] content) {
        this.fileId = fileId;
        this.name = name;
        this.size = size;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.content = content;
    }

    // Getters and setters
    public String getFileId() { return fileId; }
    public String getName() { return name; }
    public long getSize() { return size; }
    public Date getCreatedAt() { return createdAt; }
    public Date getModifiedAt() { return modifiedAt; }
    public byte[] getContent() { return content; }

    public void setContent(byte[] content) {
        this.content = content;
        this.modifiedAt = new Date(size); // Update modified time when content changes.
    }

}
