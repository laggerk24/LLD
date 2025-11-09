// Represents a single node in a doubly linked list used for each bucket in the HashMap
class Node<K, V> {
    K key;   // Key stored in this node
    V val;   // Value associated with the key
    Node next; // Pointer to the next node in the list
    Node prev; // Pointer to the previous node in the list

    // Constructor to create a node with given key and value
    Node(K key, V val){
        this.key = key;
        this.val = val;
    }

    // Getter and setter methods for encapsulation
    public K getKey() { return key; }
    public void setKey(K key) { this.key = key; }

    public V getVal() { return val; }
    public void setVal(V val) { this.val = val; }

    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }

    public Node getPrev() { return prev; }
    public void setPrev(Node prev) { this.prev = prev; }
}

// Custom implementation of a basic HashMap using an array of doubly linked lists (buckets)
public class CustomHashMap<K, V> {
    // Default starting capacity of the hash table
    private final int INITIAL_SIZE = 4;

    // Maximum capacity allowed for the hash table
    private final int MAX_CAPACITY = 1 << 30;

    // Load factor threshold to trigger resizing (rehashing)
    private final float LOAD_FACTOR = 0.75f;

    // Tracks the total number of key-value pairs in the map
    private int countOfNodes = 0;

    // Array of buckets (each bucket is a doubly linked list)
    private Node[] map;

    // Constructor to initialize the hash table with empty linked lists
    public CustomHashMap(){
        map = new Node[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++) {
            // Each bucket starts with a dummy head and tail to simplify insert/remove logic
            map[i] = new Node<>(null, null);
            map[i].next = new Node<>(null, null);
            map[i].next.prev = map[i];
        }
    }

    // Searches for a node with the given key in its corresponding bucket
    public Node findNode(K key){
        // Compute bucket index from hash code
        int bucket = key.hashCode() % map.length;
        Node head = map[bucket];

        // Traverse the doubly linked list to find the node
        while(head != null){
            if(head.key != null && head.key.equals(key)){
                return head; // Return node if key matches
            }
            head = head.next;
        }

        // Return null if key was not found
        return null;
    }

    // Fetches the value for a given key. Returns null if the key doesn’t exist.
    public V get(K key){
        Node node = findNode(key);
        return node == null ? null : (V) node.val;
    }

    // Inserts a new key-value pair or updates an existing key with a new value
    public void put(K key, V val){
        Node node = findNode(key);

        // If key already exists, just update its value
        if(node != null){
            node.val = val;
            return;
        }

        // Compute the index (bucket) where this key should go
        int bucket = key.hashCode() % map.length;
        Node head = map[bucket];
    /*
     ✅ Case 1: Bucket has only dummy head and tail (empty list)
        Before insertion:
            head <-> tail

        After insertion:
            head <-> newNode <-> tail
        → The new node is correctly added between head and tail.

     ✅ Case 2: Bucket already has some nodes
        Suppose the list looks like:
            head <-> A <-> B <-> tail

        During insertion:
            old_first = A
            We insert newNode between head and A

        After insertion:
            head <-> newNode <-> A <-> B <-> tail

        → The new node becomes the first “real” entry in the bucket.
          Existing nodes (A, B, etc.) shift naturally because we adjust pointers.
          Insertion always happens at the front, ensuring O(1) time complexity.
    */
        Node newNode = new Node(key, val);
        Node old_first = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = old_first;
        old_first.prev = newNode;

        countOfNodes++;

        // If current load exceeds the threshold, increase capacity
        if(countOfNodes > LOAD_FACTOR * map.length){
            rehash(map.length * 2);
        }
    }


    // Removes a key-value pair from the map if it exists
    public void remove(K key){
        Node nodeToRemove = findNode(key);

        // If key not found, do nothing
        if (nodeToRemove == null) return;

        // Bypass the node to remove it from the doubly linked list
        Node prevNode = nodeToRemove.prev;
        Node nextNode = nodeToRemove.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        countOfNodes--;
    }

    // Returns total number of entries in the map
    public int getSize(){
        return countOfNodes;
    }

    // Rehashes (resizes) the hash table when the load factor exceeds the threshold
    private void rehash(int newSize) {
        // Prevent growing beyond maximum capacity
        if(newSize > MAX_CAPACITY){
            System.out.println("Hashmap is exceeding max capacity");
            return;
        }

        // Create a new, larger array of buckets
        Node[] newMap = new Node[newSize];
        for (int i = 0; i < newSize; i++) {
            // Initialize each bucket with dummy head and tail nodes
            newMap[i] = new Node<>(null, null);
            newMap[i].next = new Node<>(null, null);
            newMap[i].next.prev = newMap[i];
        }

        // Iterate through all current buckets and move their nodes to the new map
        for (Node<K, V> curr : map) {
            while (curr != null) {
                // Skip dummy head/tail nodes which have null keys
                if (curr.key == null){
                    curr = curr.next;
                    continue;
                }

                // Calculate new bucket index in resized array
                int newBucket = curr.key.hashCode() % newSize;
                Node newHead = newMap[newBucket];
                Node oldFirst = newHead.next;

                // Temporarily store next pointer before re-linking
                Node nextNode = curr.next;

                // Insert current node at the start of the new bucket
                newHead.next = curr;
                curr.prev = newHead;
                curr.next = oldFirst;
                oldFirst.prev = curr;

                // Move to next node in the old list
                curr = nextNode;
            }
        }

        // Replace the old map with the new, resized map
        map = newMap;
    }
}
