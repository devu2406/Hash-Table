import java.util.*;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
static void main() {
}
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

class MultiLevelCache {

    LRUCache<String, String> L1 = new LRUCache<>(10000);
    LRUCache<String, String> L2 = new LRUCache<>(100000);

    Map<String, String> database = new HashMap<>();

    public String getVideo(String id) {

        if (L1.containsKey(id))
            return L1.get(id);

        if (L2.containsKey(id)) {

            String video = L2.get(id);

            L1.put(id, video);

            return video;
        }

        String video = database.get(id);

        if (video != null)
            L2.put(id, video);

        return video;
    }
}