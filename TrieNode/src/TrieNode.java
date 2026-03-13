import java.util.*;

class TrieNode {
    static void main() {
    }

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}

class AutocompleteSystem {

    TrieNode root = new TrieNode();
    Map<String, Integer> frequency = new HashMap<>();

    public void insert(String query) {

        TrieNode node = root;

        for (char c : query.toCharArray()) {

            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;

        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> searchPrefix(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c))
                return new ArrayList<>();

            node = node.children.get(c);
        }

        List<String> result = new ArrayList<>();
        dfs(node, prefix, result);

        result.sort((a, b) -> frequency.get(b) - frequency.get(a));

        return result.subList(0, Math.min(10, result.size()));
    }

    private void dfs(TrieNode node, String word, List<String> result) {

        if (node.isWord)
            result.add(word);

        for (char c : node.children.keySet())
            dfs(node.children.get(c), word + c, result);
    }
}