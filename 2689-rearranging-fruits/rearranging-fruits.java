import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int minVal = Integer.MAX_VALUE;

        for (int b : basket1) {
            countMap.put(b, countMap.getOrDefault(b, 0) + 1);
            minVal = Math.min(minVal, b);
        }

        for (int b : basket2) {
            countMap.put(b, countMap.getOrDefault(b, 0) - 1);
            minVal = Math.min(minVal, b);
        }

        List<Integer> fromB1 = new ArrayList<>();
        List<Integer> fromB2 = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int fruit = entry.getKey();
            int count = entry.getValue();

            if (count % 2 != 0) return -1;

            int half = Math.abs(count) / 2;
            if (count > 0) {
                for (int i = 0; i < half; i++) fromB1.add(fruit);
            } else if (count < 0) {
                for (int i = 0; i < half; i++) fromB2.add(fruit);
            }
        }

       
        Collections.sort(fromB1);
        Collections.sort(fromB2, Collections.reverseOrder());

        long cost = 0;
        for (int i = 0; i < fromB1.size(); i++) {
            int a = fromB1.get(i);
            int b = fromB2.get(i);
            cost += Math.min(Math.min(a, b), 2 * minVal);
        }

        return cost;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] basket1 = {4, 2, 2, 2};
        int[] basket2 = {1, 4, 1, 2};
        System.out.println(sol.minCost(basket1, basket2)); 
    }
}
