public class Document {
    private final String content;
    private final Integer id;
    private final String documentName;

    public Document(Integer id, String content, String documentName) {
        this.content = content;
        this.id = id;
        this.documentName = documentName;
    }

    public String getContent() {
        return content;
    }

    public Integer getId() {
        return id;
    }
}
