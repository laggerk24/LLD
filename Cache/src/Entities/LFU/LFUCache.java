package Entities.LFU;

import Entities.Cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, LFUNode<K, V>> map = new HashMap<>();
    private final Map<Integer, LFUDoublyLinkedList<K, V>> freqMap = new HashMap<>();
    private int minFreq = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized V get(K key) {
        if (!map.containsKey(key)) return null;

        LFUNode<K, V> node = map.get(key);
        increaseFrequency(node);
        return node.value;
    }

    @Override
    public synchronized void put(K key, V value) {
        if (capacity == 0) return;

        if (map.containsKey(key)) {
            LFUNode<K, V> node = map.get(key);
            node.value = value;
            increaseFrequency(node);
        } else {
            if (map.size() == capacity) {
                // Evict least frequently used
                LFUDoublyLinkedList<K, V> list = freqMap.get(minFreq);
                LFUNode<K, V> toRemove = list.removeLast();
                map.remove(toRemove.key);
            }

            LFUNode<K, V> newNode = new LFUNode<>(key, value);
            map.put(key, newNode);
            minFreq = 1;

            freqMap.computeIfAbsent(1, x -> new LFUDoublyLinkedList<>())
                    .addFirst(newNode);
        }
    }

    @Override
    public synchronized void remove(K key) {
        if (!map.containsKey(key)) return;

        LFUNode<K, V> node = map.get(key);
        int freq = node.frequency;

        LFUDoublyLinkedList<K, V> list = freqMap.get(freq);
        list.remove(node);

        if (list.isEmpty() && freq == minFreq) {
            minFreq++;
        }

        map.remove(key);
    }

    private void increaseFrequency(LFUNode<K, V> node) {
        int oldFreq = node.frequency;
        int newFreq = oldFreq + 1;
        node.frequency = newFreq;

        LFUDoublyLinkedList<K, V> oldList = freqMap.get(oldFreq);
        oldList.remove(node);

        if (oldList.isEmpty() && oldFreq == minFreq) {
            minFreq++;
        }

        freqMap.computeIfAbsent(newFreq, x -> new LFUDoublyLinkedList<>())
                .addFirst(node);
    }

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Override
    public int capacity() {
        return capacity;
    }
}

