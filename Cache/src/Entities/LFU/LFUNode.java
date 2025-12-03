package Entities.LFU;

import Entities.LRU.Node;

public class LFUNode<K, V> {
    K key;
    V value;
    int frequency = 1;
    LFUNode<K, V> prev, next;

    public LFUNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
