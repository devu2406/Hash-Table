import java.util.*;

class PlagiarismDetector {
    static void main() {
    }

    Map<String, Set<String>> index = new HashMap<>();

    public List<String> extractNGrams(String text, int n) {

        String[] words = text.split("\\s+");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++)
                gram.append(words[i + j]).append(" ");

            grams.add(gram.toString().trim());
        }

        return grams;
    }

    public void indexDocument(String docId, String text) {

        for (String gram : extractNGrams(text, 5)) {

            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }
    }

    public Map<String, Integer> findSimilar(String text) {

        Map<String, Integer> similarity = new HashMap<>();

        for (String gram : extractNGrams(text, 5)) {

            if (index.containsKey(gram)) {

                for (String doc : index.get(gram)) {
                    similarity.put(doc, similarity.getOrDefault(doc, 0) + 1);
                }
            }
        }

        return similarity;
    }
}