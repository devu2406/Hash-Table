import java.util.*;

class UsernameChecker {
    static void main() {

    }

    private Map<String, Integer> usernameToId = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !usernameToId.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        usernameToId.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String candidate = username + i;
            if (!usernameToId.containsKey(candidate))
                suggestions.add(candidate);
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    public String getMostAttempted() {
        return Collections.max(attemptFrequency.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }
}