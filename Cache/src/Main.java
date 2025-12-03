import Entities.LFU.LFUCache;
import Entities.LRU.LRUCache;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("===== LRU Demo =====");
        LRUCache<String, Integer> lru = new LRUCache<>(3);

        lru.put("a", 1);
        lru.put("b", 2);
        lru.put("c", 3);

        System.out.println("get(a) -> " + lru.get("a")); // 1

        lru.put("d", 4);  // Evicts "b" (LRU)

        System.out.println("get(b) -> " + lru.get("b")); // null
        System.out.println("get(d) -> " + lru.get("d")); // 4



        System.out.println("\n===== LFU Demo =====");
        LFUCache<String, Integer> lfu = new LFUCache<>(3);

        lfu.put("a", 1);
        lfu.put("b", 2);
        lfu.put("c", 3);

        // Access pattern to create frequency differences
        lfu.get("a"); // freq(a)=2
        lfu.get("a"); // freq(a)=3
        lfu.get("b"); // freq(b)=2
        // c freq=1

        lfu.put("d", 4); // Evicts "c" (LFU)

        System.out.println("get(c) -> " + lfu.get("c")); // null
        System.out.println("get(d) -> " + lfu.get("d")); // 4
    }
}