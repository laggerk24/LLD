import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SearchEngine {
    private static SearchEngine instance;
    private final WordIndexer indexer;
    private final ConcurrentHashMap<Integer,Document> map;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    private SearchEngine() {
        this.indexer = WordIndexer.getInstance();
        map = new ConcurrentHashMap<>();
    }

    public static SearchEngine getInstance(){
        if(instance == null){
            synchronized (SearchEngine.class){
                if(instance == null){
                    instance = new SearchEngine();
                }
            }
        }
        return instance;
    }

    public void parseDocument(String content,String docName){
        Integer id = atomicInteger.getAndIncrement();
        Document doc = new Document(id,content,docName);
        indexer.parseDocument(doc);
        map.put(id,doc);
    }

    public List<Document> searchDocument(String word){
        HashSet<Integer> docIds = indexer.getDocuments(word);
        return docIds.stream().map(map::get).collect(Collectors.toList());
    }
}
