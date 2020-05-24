import com.sun.istack.internal.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    public static FileManager sharedInstance = new FileManager();

    private ArrayList<File> files = new ArrayList<>();
    private int lastProcessedFileIndex = 0;

    private FileManager() {
        try {
            Files.walk(Paths.get("./data"))
                    .filter(Files::isRegularFile)
                    .forEach((Path path) -> { files.add(path.toFile()); });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public String getNextFile() {
        String address = null;

        if (lastProcessedFileIndex < files.size()) {
            address = files.get(lastProcessedFileIndex).getAbsolutePath();
            lastProcessedFileIndex++;
        }

        return address;
    }
}
