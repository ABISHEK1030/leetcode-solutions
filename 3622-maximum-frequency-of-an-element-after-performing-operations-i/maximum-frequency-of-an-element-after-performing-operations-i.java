class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
      Map<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            diff.putIfAbsent(v, 0);
            diff.put(v - k, diff.getOrDefault(v - k, 0) + 1);
            diff.put(v + k + 1, diff.getOrDefault(v + k + 1, 0) - 1);
        }
        
        int running = 0;
        int answer = 0;
        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            int x = entry.getKey();
            running += entry.getValue();   
            
            int already = cnt.getOrDefault(x, 0); 
            int reachableOthers = running - already;
            if (reachableOthers < 0) reachableOthers = 0;
            
            int possible = already + Math.min(numOperations, reachableOthers);
            answer = Math.max(answer, possible);
        }
        
        return answer;
    }
}