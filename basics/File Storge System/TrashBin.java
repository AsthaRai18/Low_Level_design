import java.util.ArrayList;
import java.util.List;

class TrashBin {
    private List<FileItem> trashedFiles = new ArrayList<>();

    public void moveToTrash(FileItem file) {
        trashedFiles.add(file);
        System.out.println("Moved file to trash: " + file.getName());
    }

    public void restoreFile(FileItem file, Folder folder) {
        if (trashedFiles.remove(file)) {
            folder.addFile(file);
            System.out.println("Restored file: " + file.getName());
        } else {
            System.out.println("File not found in trash: " + file.getName());
        }
    }

    public void emptyTrash() {
        trashedFiles.clear();
        System.out.println("Trash emptied.");
    }

    public List<FileItem> getTrashedFiles() {
        return trashedFiles;
    }
}
