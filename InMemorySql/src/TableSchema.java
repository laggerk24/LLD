import java.util.HashMap;

public class TableSchema {
    private final HashMap<String,Class<?>> schema;

    public TableSchema(HashMap<String, Class<?>> schema) {
        this.schema = schema;
    }

    public HashMap<String, Class<?>> getSchema() {
        return schema;
    }
}
