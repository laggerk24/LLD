import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Database implements DatabaseInterface {
    private final ConcurrentHashMap<String,Table<?>> tables;
    private final String databaseName;

    public Database(String databaseName) {
        this.databaseName = databaseName;
        this.tables = new ConcurrentHashMap<>();
    }

    @Override
    public <K> void createTable(String tableName, HashMap<String, Class<?>> tableSchema) {
        if(tables.containsKey(tableName)){
            throw new TableAlreadyExistException(tableName + " already exist in the database " + databaseName);
        }
        Table<K> table = new Table<>(tableName,new TableSchema(tableSchema));
        tables.put(tableName,table);
    }

    @Override
    public void dropTable(String tableName) {
        tables.remove(tableName);
    }

    @Override
    public String getRowFromTable(String tableName, Object key){
        if(!tables.containsKey(tableName)){
            throw new TableNotFoundException(tableName + " not present in " + databaseName);
        }
        Table<Object> table = (Table<Object>) tables.get(tableName);
        return table.getRow(key);
    }

    @Override
    public String getAllRows(String tableName){
        if(!tables.containsKey(tableName)){
            throw new TableNotFoundException(tableName + " not present in " + databaseName);
        }
        return tables.get(tableName).getAllRows();
    }

    @Override
    public <K> String insertIntoTable(String tableName, K key, HashMap<String, Object> rowData) throws AttributeNotFoundException {
        if(!tables.containsKey(tableName)){
            throw new TableNotFoundException(tableName + " not present in " + databaseName);
        }
        Table<Object> table = (Table<Object>) tables.get(tableName);
        table.insertRow(key,rowData);
        return table.getRow(key);
    }

    @Override
    public <K> String updateTable(String tableName, K key, HashMap<String, Object> rowData) throws AttributeNotFoundException {
        if(!tables.containsKey(tableName)){
            throw new TableNotFoundException(tableName + " not present in " + databaseName);
        }
        Table<Object> table = (Table<Object>) tables.get(tableName);
        table.updateRow(key,rowData);
        return table.getRow(key);
    }
}
