import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface DatabaseInterface {
    <K> void addTable(String tableName, TableSchema schema);
    void dropTable(String tableName);
    ConcurrentHashMap getRowFromTable(String tableName, Object key);
    ConcurrentHashMap<?, Row> getAllRows(String tableName);
    public <K> ConcurrentHashMap<String, Object> insertIntoTable(String tableName, K key, HashMap<String, Object> rowData) throws AttributeNotFoundException;
    public <K> ConcurrentHashMap<String, Object> updateTable(String tableName, K key, HashMap<String, Object> rowData) throws AttributeNotFoundException;
}
