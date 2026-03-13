package src;

import java.util.*;

class DNSEntry {
    static void main() {
    }
    String ip;
    long expiry;

    DNSEntry(String ip, long ttlSeconds) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiry;
    }
}

class DNSCache {

    private Map<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return entry.ip;
            }

            cache.remove(domain);
        }

        misses++;

        // Simulated upstream DNS
        String newIP = "172.217.14." + new Random().nextInt(255);

        cache.put(domain, new DNSEntry(newIP, 300));

        return newIP;
    }

    public void getStats() {
        int total = hits + misses;
        System.out.println("Hit Rate: " + (hits * 100.0 / total) + "%");
    }
}