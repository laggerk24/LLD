import javax.management.AttributeNotFoundException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table<K> implements TableInteface<K>{
    private final ConcurrentHashMap<K,Row> rows;
    private final String tableName;
    private final TableSchema tableSchema;
    private final ReadWriteLock readWriteLock;

    public Table(String tableName, TableSchema tableSchema) {
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        rows = new ConcurrentHashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public Row insertRow(K key, HashMap<String, Object> value) throws AttributeNotFoundException {
        readWriteLock.writeLock().lock();
        try{
            if(rows.containsKey(key)){
                throw new KeyAlreadyExistsException(key + " already exists in table " + tableName);
            }

            HashMap<String,Class<?>> schema = tableSchema.getSchema();
            Row row = new Row();

            for(String column: schema.keySet()){
                row.setRowValue(column,null);
            }
            for(String k: value.keySet()){
                if(!schema.containsKey(k)){
                    throw new AttributeNotFoundException(key + " not present in table " + tableName);
                } else if(schema.get(k) != value.get(k).getClass()){
                    throw new RuntimeException("Incorrect data type of key " + k + " expected value " + schema.get(k) + " provided " + value.get(k).getClass());
                }
                else {
                    row.setRowValue(k,value.get(k));
                }
            }
            return rows.put(key,row);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override
    public Row updateRow(K key, HashMap<String, Object> value) throws AttributeNotFoundException {
        readWriteLock.writeLock().lock();
        try{
            if(!rows.containsKey(key)){
                throw new AttributeNotFoundException(key + " not present in table " + tableName);
            }
            Row row = rows.get(key);
            HashMap<String,Class<?>> schema = tableSchema.getSchema();
            for(String k: value.keySet()){
                if(!schema.containsKey(k)){
                    throw new AttributeNotFoundException(key + " not present in table " + tableName);
                } else if(schema.get(k) != value.get(k).getClass()){
                    throw new RuntimeException("Incorrect data type of key " + k + " expected value " + schema.get(k) + " provided " + value.get(k).getClass());
                }
                else {
                    row.setRowValue(k,value.get(k));
                }
            }
            return row;
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override
    public void deleteRow(K key) {
        rows.remove(key);
    }

    @Override
    public ConcurrentHashMap<String, Object> getRow(K key) {
        return rows.get(key).getData();
    }

    @Override
    public ConcurrentHashMap<K, Row> getAllRows() {
        return rows;
    }

}
