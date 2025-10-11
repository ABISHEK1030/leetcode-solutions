class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Long> map = new HashMap<>();
        for (int p : power) {
            map.put(p, map.getOrDefault(p, 0L) + p);
        }

        List<Integer> nums = new ArrayList<>(map.keySet());
        Collections.sort(nums);

        int n = nums.size();
        long[] dp = new long[n];
        dp[0] = map.get(nums.get(0));

        for (int i = 1; i < n; i++) {
            long take = map.get(nums.get(i));
            int j = i - 1;
         
            while (j >= 0 && nums.get(j) >= nums.get(i) - 2) {
                j--;
            }
            if (j >= 0) take += dp[j];
            long skip = dp[i - 1];
            dp[i] = Math.max(take, skip);
        }

        return dp[n - 1];
    }
}