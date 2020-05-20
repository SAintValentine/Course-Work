import java.io.File;

public class FileManager {
    static int counter = -1;

    String get() {
        File dir = new File("./data");
        File[] children = dir.listFiles();
        counter++;
        return children[counter].getAbsolutePath();
    }
}
