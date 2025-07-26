import java.util.concurrent.ConcurrentHashMap;

public interface DatabaseInterface {
    <K> void addTable(String tableName, TableSchema schema);
    void dropTable(String tableName);
    ConcurrentHashMap getRowFromTable(String tableName, Object key);
}
