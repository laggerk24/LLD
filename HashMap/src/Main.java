//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("Alice", 85);
        map.put("John", 78);
        map.put("Tom", 82);

        System.out.println(map.get("John"));
        System.out.println(map.get("Bob"));
    }
}