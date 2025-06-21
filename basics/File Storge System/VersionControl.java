import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class VersionControl {
    // Mapping from file ID to a list of versions.
    private Map<String, List<FileItem>> versionHistory = new HashMap<>();

    public void saveVersion(FileItem file) {
        versionHistory.computeIfAbsent(file.getFileId(), k -> new ArrayList<>()).add(file);
        System.out.println("Version saved for file: " + file.getName());
    }

    public List<FileItem> getVersions(String fileId) {
        return versionHistory.getOrDefault(fileId, new ArrayList<>());
    }

    public FileItem revertToVersion(String fileId, int versionIndex) {
        List<FileItem> versions = versionHistory.get(fileId);
        if (versions == null || versions.isEmpty()) {
            throw new RuntimeException("No versions available for file: " + fileId);
        }
        if (versionIndex < 0 || versionIndex >= versions.size()) {
            throw new IllegalArgumentException("Invalid version index.");
        }
        FileItem version = versions.get(versionIndex);
        System.out.println("Reverted file " + fileId + " to version index " + versionIndex);
        return version;
    }
}
