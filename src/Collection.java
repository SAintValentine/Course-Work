import com.sun.istack.internal.Nullable;

import java.util.*;

public class Collection {

    public static Collection sharedInstance = new Collection();

    private HashMap<String, ArrayList<Element>> res = new HashMap<>();
    private Set<String> queue = new HashSet<>();

    private Collection() { }

    public boolean addToRes(Element element) {
        if (queue.contains(element.getTerm())) {
            return false;
        } else {
            queue.add(element.getTerm());

            if (!res.containsKey(element.getTerm())) {
                res.put(element.getTerm(), new ArrayList<>());
            }

            ArrayList<Element> list = res.get(element.getTerm());
            if (list != null) {
                try {
                    list.add(element);
                } catch (IndexOutOfBoundsException e) {
                    list.ensureCapacity(list.size());
                    list.add(element);
                }
            }

            queue.remove(element.getTerm());

            return true;
        }
    }

    public void printCollection() {
        for (ArrayList<Element> list :
                res.values()) {
            for (Element element :
                    list) {
                if (element != null) {
                    System.out.println(element.toString());
                }
            }
        }
    }

    public ArrayList<SearchResult> findString(String string) {
        ArrayList<SearchResult> results = new ArrayList<>();
        String[] words = string.toLowerCase().split("[ .,:;\\-?!'\"\n\t()<>/]+");

        if (res.containsKey(words[0])) {
            ArrayList<Element> list = res.get(words[0]);
            for (Element element : list) {
                if (words.length == 1 || findSequence(element, words, 1)) {
                    results.add(new SearchResult(element.getDocument(), element.getPosition()));
                }
            }
        }

        return results;
    }

    private boolean findSequence(Element previous, String[] words, int position) {
        if (res.containsKey(words[position])) {
            ArrayList<Element> list = res.get(words[position]);

            for (Element element : list) {
                if (element.getDocument().contentEquals(previous.getDocument()) && element.getPosition() == previous.getPosition() + 1) {
                    if (position == words.length - 1) {
                        return true;
                    } else {
                        return findSequence(element, words, position + 1);
                    }
                }
            }

            return false;
        } else {
            return false;
        }
    }

}