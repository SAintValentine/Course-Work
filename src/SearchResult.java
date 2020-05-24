public class SearchResult {

    private String document;
    private int position;

    SearchResult(String document, int position) {
        this.document = document;
        this.position = position;
    }

    public String getDocument() {
        return document;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        String representation = "";
        representation += "Document path: ";
        representation += document;
        representation += "\n";
        representation += "Position: ";
        representation += (position + 1);
        representation += "\n";

        return representation;
    }
}
