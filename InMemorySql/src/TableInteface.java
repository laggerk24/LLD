import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface TableInteface<K> {
    Row insertRow(K Key, HashMap<String, Object> value) throws AttributeNotFoundException;
    Row updateRow(K key, HashMap<String, Object> val) throws AttributeNotFoundException;
    void deleteRow(K key);
    String getRow(K key);
    String getAllRows();
}
