import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Row {
    ConcurrentHashMap<String, Object> data;
    public Row() {
        this.data = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Object> getData() {
        return data;
    }

    public void setRowValue(String key, Object value){
        data.put(key,value);
    }
}
