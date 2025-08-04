import java.util.List;

public class Main {
    public static void main(String[] args) {
        SearchEngine engine = SearchEngine.getInstance();
        engine.parseDocument("Hello, dear my name is Lagger","lagger_salutation");
        engine.parseDocument("Nice to meet u gorgeous is","mas_salutation");
        engine.parseDocument("Hello, dear my name is Lagger","lagger_salutation2");
        List<Document> result = engine.searchDocument("is");
        for (Document d: result){
            System.out.println(d.getId());
        }
    }
}