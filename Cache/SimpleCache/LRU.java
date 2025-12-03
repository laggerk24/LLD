import java.util.HashMap;

class Node {
    public Node prev, next;
    public int key, value;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DoublyLL {
    Node head, tail;

    public DoublyLL() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    // Add a node right after head (MRU position)
    public void addFront(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    // Remove a specific node
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Remove the LRU node (just before tail)
    public Node removeLast() {
        if (tail.prev == head) return null; // no real nodes
        Node lru = tail.prev;
        remove(lru);
        return lru;
    }
}

class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> map;
    private DoublyLL dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLL();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        dll.remove(node);
        dll.addFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            dll.remove(node);
            dll.addFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = dll.removeLast();
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            dll.addFront(newNode);
            map.put(key, newNode);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1

        cache.put(3, 3); // removes key 2
        System.out.println(cache.get(2)); // returns -1 (not found)

        cache.put(4, 4); // removes key 1
        System.out.println(cache.get(1)); // returns -1
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
}
