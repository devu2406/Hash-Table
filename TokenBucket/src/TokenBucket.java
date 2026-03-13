import java.util.*;

class TokenBucket {
    static void main() {
    }

    int tokens;
    int maxTokens;
    long lastRefill;

    public TokenBucket(int maxTokens) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }

    synchronized boolean allowRequest() {

        long now = System.currentTimeMillis();

        if (now - lastRefill > 3600000) {
            tokens = maxTokens;
            lastRefill = now;
        }

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }
}

class RateLimiter {

    Map<String, TokenBucket> clients = new HashMap<>();

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(1000));

        return clients.get(clientId).allowRequest();
    }
}