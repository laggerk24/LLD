package Entities.LFU;

public class LFUDoublyLinkedList<K, V> {
    private final LFUNode<K, V> head;
    private final LFUNode<K, V> tail;

    LFUDoublyLinkedList() {
        head = new LFUNode<>(null, null);
        tail = new LFUNode<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    void addFirst(LFUNode<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    LFUNode<K, V> removeLast() {
        if (tail.prev == head) return null;
        LFUNode<K, V> last = tail.prev;
        remove(last);
        return last;
    }

    void remove(LFUNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    boolean isEmpty() {
        return head.next == tail;
    }
}
