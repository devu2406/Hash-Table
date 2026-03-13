import java.util.*;

class FlashSaleInventory {
    static void main() {
    }

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public synchronized void addProduct(String productId, int count) {
        stock.put(productId, count);
        waitingList.put(productId, new LinkedList<>());
    }

    public synchronized String checkStock(String productId) {
        return stock.getOrDefault(productId, 0) + " units available";
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int current = stock.getOrDefault(productId, 0);

        if (current > 0) {
            stock.put(productId, current - 1);
            return "Success, remaining: " + (current - 1);
        } else {
            Queue<Integer> queue = waitingList.get(productId);
            queue.add(userId);
            return "Added to waiting list position #" + queue.size();
        }
    }
}