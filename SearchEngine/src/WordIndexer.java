import javax.print.Doc;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WordIndexer {
    private static WordIndexer instance;
    private final HashMap<String, HashSet<Integer>> wordIndexer;

    private WordIndexer() {
        this.wordIndexer = new HashMap<>();
    }

    public static WordIndexer getInstance(){
        if(instance == null){
            synchronized (WordIndexer.class){
                if (instance == null){
                    instance = new WordIndexer();
                }
            }
        }
        return instance;
    }

    public void parseDocument(Document document){
        String[] words = document.getContent().split("\\W+");
        for(String word : words){
            wordIndexer.computeIfAbsent(word, k -> new HashSet<>()).add(document.getId());
//            wordIndexer.computeIfAbsent(word,k -> new HashSet<>(document.getId()));
        }
    }

    public HashSet<Integer> getDocuments(String word){
        return wordIndexer.get(word);
    }
}
