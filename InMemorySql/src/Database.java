import java.util.concurrent.ConcurrentHashMap;

public class Database implements DatabaseInterface {
    private final ConcurrentHashMap<String,Table<?>> tables;
    private final String databaseName;

    public Database(String databaseName) {
        this.databaseName = databaseName;
        this.tables = new ConcurrentHashMap<>();
    }

    @Override
    public <K> void addTable(String tableName, TableSchema schema) {
        if(tables.containsKey(tableName)){
            throw new TableAlreadyExistException(tableName + " already exist in the database " + databaseName);
        }
        Table<K> table = new Table<>(tableName,schema);
        tables.put(tableName,table);
    }

    @Override
    public void dropTable(String tableName) {
        tables.remove(tableName);
    }

    @Override
    public ConcurrentHashMap<String,Object> getRowFromTable(String tableName, Object key){
        if(!tables.containsKey(tableName)){
            throw new TableNotFoundException(tableName + " not present in " + databaseName);
        }
        Table<?> table = tables.get(tableName);
        return table.getRow(key);
    }

    public ConcurrentHashMap<?, Row> getAllRows(String tableName){
        if(!tables.containsKey(tableName)){
            throw new TableNotFoundException(tableName + " not present in " + databaseName);
        }
        return tables.get(tableName).getAllRows();
    }
}
