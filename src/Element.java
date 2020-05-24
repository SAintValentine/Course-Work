public class Element {

    private String term;
    private String document;
    private int position;

    Element(String term, String document, int position) {
        this.term = term;
        this.document = document;
        this.position = position;
    }

    public String getTerm() {
        return term;
    }

    public String getDocument() {
        return document;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        String representation = "";
        representation += "Term: ";
        representation += term;
        representation += "\n";
        representation += "Document path: ";
        representation += document;
        representation += "\n";
        representation += "Position: ";
        representation += (position + 1);
        representation += "\n";

        return representation;
    }
}
