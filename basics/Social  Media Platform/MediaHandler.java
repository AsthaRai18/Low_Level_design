import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class MediaHandler {
    private String storagePath;

    public MediaHandler(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Uploads a media file.
     *
     * @param mediaFile The InputStream of the media file.
     * @param filename  The name under which the file will be saved.
     * @return The file path where the media is stored.
     * @throws IOException If an I/O error occurs.
     */
    public String uploadMedia(InputStream mediaFile, String filename) throws IOException {
        // Ensure the storage directory exists
        File dir = new File(storagePath);
        if (!dir.exists()) {
            dir.mkdirs();   
        }

        String filePath = storagePath + File.separator + filename;
        Path destination = Paths.get(filePath);

        // Copy the file (overwrites if already exists)
        Files.copy(mediaFile, destination, StandardCopyOption.REPLACE_EXISTING);
        return filePath;
    }
}
