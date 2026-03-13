import java.util.*;

class AnalyticsDashboard {

    static void main() {

    }

    Map<String, Integer> pageViews = new HashMap<>();
    Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    Map<String, Integer> trafficSource = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSource.put(source, trafficSource.getOrDefault(source, 0) + 1);
    }

    public void getTopPages() {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            var e = pq.poll();
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}