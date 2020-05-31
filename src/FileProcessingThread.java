import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileProcessingThread extends Thread {

    private ArrayList<Element> processing = new ArrayList<>();
    private String currentFileContent;
    private String currentFilePath;

    private boolean getNextFileContent() {
        String filePath = FileManager.sharedInstance.getNextFile();

        if (filePath != null) {
            try {
                currentFilePath = filePath;
                currentFileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private void processContent() {
        String[] terms = currentFileContent.toLowerCase().split("[ .,:;\\-?!'\"\n\t()<>/]+");

        for (int i = 0; i < terms.length; i++) {
            Element element = new Element(terms[i], currentFilePath, i);
            processing.add(element);

            for (int j = processing.size() - 1; j >= 0; j--) {
                Element term = processing.get(j);

                if (Collection.sharedInstance.addToRes(term)) {
                    processing.remove(term);
                }
            }
        }

        while (!processing.isEmpty()) {
            for (int j = processing.size() - 1; j >= 0; j--) {
                Element term = processing.get(j);

                if (Collection.sharedInstance.addToRes(term)) {
                    processing.remove(term);
                }
            }
        }
    }

    @Override
    public void run() {
        while(getNextFileContent()) {
            processContent();
        }
    }

}
