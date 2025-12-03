package Factory;

import Entities.*;
import Entities.LFU.LFUCache;
import Entities.LRU.LRUCache;
import Enum.*;

public class CacheFactory {
    public static <K, V> Cache<K, V> create(CacheType type, int capacity) {
        return switch (type) {
            case LRU -> new LRUCache<>(capacity);
            case LFU -> new LFUCache<>(capacity);
        };
    }
}
