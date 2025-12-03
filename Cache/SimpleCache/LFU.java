import java.util.HashMap;

class Node {
    int key;
    int val;
    Node next;
    Node prev;
    int freq = 1;

    Node(int k, int v) {
        key = k;
        val = v;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    DoublyLinkedList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    void addNode(Node v) {
        Node next = head.next;
        head.next = v;
        v.prev = head;
        v.next = next;
        next.prev = v;
    }

    Node removeNode() {
        if (head.next == tail) return null; // nothing to remove
        Node node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        return node;
    }

    Node removeNode(Node v) {
        v.prev.next = v.next;
        v.next.prev = v.prev;
        return v;
    }

    boolean isEmpty() {
        return head.next == tail;
    }
}

class LFUCache {
    HashMap<Integer, DoublyLinkedList> freqList = new HashMap<>();
    HashMap<Integer, Node> lfuCache = new HashMap<>();
    int capacity;
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 1;
    }

    public int get(int key) {
        if (!lfuCache.containsKey(key))
            return -1;

        Node v = lfuCache.get(key);

        freqList.get(v.freq).removeNode(v);

        if (freqList.get(v.freq).isEmpty()) {
            if (minFreq == v.freq) {
                minFreq = v.freq + 1;
            }
        }

        v.freq++;

        freqList.computeIfAbsent(v.freq, k -> new DoublyLinkedList())
                .addNode(v);

        return v.val;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (lfuCache.containsKey(key)) {
            Node v = lfuCache.get(key);

            freqList.get(v.freq).removeNode(v);
            if (freqList.get(v.freq).isEmpty() && minFreq == v.freq)
                minFreq = v.freq + 1;

            v.freq++;
            v.val = value;

            freqList.computeIfAbsent(v.freq, k -> new DoublyLinkedList())
                    .addNode(v);

        } else {
            if (lfuCache.size() == capacity) {
                Node node = freqList.get(minFreq).removeNode();
                lfuCache.remove(node.key);
            }

            Node newNode = new Node(key, value);
            lfuCache.put(key, newNode);

            freqList.computeIfAbsent(1, k -> new DoublyLinkedList())
                    .addNode(newNode);

            minFreq = 1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.get(1)); // returns 10 (freq of key=1 becomes 2)

        cache.put(3, 30); // removes key=2 (freq=1, least frequent)
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 30

        cache.put(4, 40); // removes key=1 (freq=2 but now least after usage)

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40
    }
}
