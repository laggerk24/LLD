import javax.xml.crypto.Data;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseManager {
    private static DatabaseManager instance;
    private final ConcurrentHashMap<String,Database> databasemanager;
    private DatabaseManager() {
        databasemanager = new ConcurrentHashMap<>();
    };

    public static DatabaseManager getInstance(){
        if(instance == null){
            synchronized (DatabaseManager.class){
                if(instance == null){
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public Database addDatabase(String databaseName){
        if(databasemanager.containsKey(databaseName)){
            throw new DatabaseAlreadyExistException(databaseName + " Database already exist");
        }
        databasemanager.put(databaseName,new Database(databaseName));
        return databasemanager.get(databaseName);
    }

    public Database getDatabase(String databaseName){
        if(!databasemanager.containsKey(databaseName))
            throw new DatabaseNotFoundException(databaseName + " do not exist please create a new one to proceed further ");
        return databasemanager.get(databaseName);
    }

}
