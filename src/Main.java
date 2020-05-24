import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Instant start = Instant.now();
        Scanner in = new Scanner(System.in);  // використаємо клас Scanner для отримання інформації з консолі
        System.out.print("Input a number of threads: ");
        int N = in.nextInt(); //запит у користувача кількості потоків

        ArrayList<FileProcessingThread> threadsArray = new ArrayList<>();
        for(int i = 0; i < N; i++) { //розбиття на потоки
            FileProcessingThread thread = new FileProcessingThread();
            threadsArray.add(thread);
            thread.start();
        }

        for(int i = 0; i < N; i++) { //розбиття на потоки
            try {
                threadsArray.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Files indexed successfully!");

        System.out.println("Indexing time: ");
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        System.out.println("Input a word or a phrase (enter 0 to quit): ");
        in.nextLine();
        String search = in.nextLine();

        while (!search.contentEquals("0")) {
            List<SearchResult> results = Collection.sharedInstance.findString(search);

            if (results.isEmpty()) {
                System.out.println("No results!");
            } else {
                System.out.println("Match(-es) can be found at:");
                results.forEach(searchResult -> { System.out.println(searchResult.toString()); });
            }

            System.out.print("Input a word or a phrase (enter 0 to quit): ");
            search = in.nextLine();
        }
    }
}
