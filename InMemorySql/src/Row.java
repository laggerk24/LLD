import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Row {
    ConcurrentHashMap<String, Object> data;
    public Row() {
        this.data = new ConcurrentHashMap<>();
    }

    public String getData() {
        StringBuilder sb = new StringBuilder();
        for(String key: data.keySet()){
            sb.append(key).append(":").append(data.get(key)).append(",");
        }
        return sb.toString();
    }

    public void setRowValue(String key, Object value){
        data.put(key,value);
    }
}
