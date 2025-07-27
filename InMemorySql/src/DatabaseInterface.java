import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface DatabaseInterface {
    <K> void createTable(String tableName, HashMap<String, Class<?>> tableSchema);
    void dropTable(String tableName);
    String getRowFromTable(String tableName, Object key);
    String getAllRows(String tableName);
    public <K> String insertIntoTable(String tableName, K key, HashMap<String, Object> rowData) throws AttributeNotFoundException;
    public <K> String updateTable(String tableName, K key, HashMap<String, Object> rowData) throws AttributeNotFoundException;
}
