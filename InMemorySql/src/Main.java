import javax.management.AttributeNotFoundException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws AttributeNotFoundException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Database employeeDb = databaseManager.addDatabase("Employee");
        HashMap<String, Class<?>> employeeTableschema = new HashMap<>();
        employeeTableschema.put("name",String.class);
        employeeTableschema.put("salary",Double.class);
        employeeDb.<Integer>createTable("employee_details",employeeTableschema);
        HashMap<String, Object> employeeData1 = new HashMap<>();
        employeeData1.put("name","Aman");
        employeeData1.put("salary",5000000D);
        employeeDb.<Integer>insertIntoTable("employee_details",1,employeeData1);
        HashMap<String, Object> employeeData2 = new HashMap<>();
        employeeData2.put("name","Lagger");
        employeeData2.put("salary",5000000D);
        employeeDb.<Integer>insertIntoTable("employee_details",2,employeeData2);
        System.out.println(employeeDb.getAllRows("employee_details"));
    }
}