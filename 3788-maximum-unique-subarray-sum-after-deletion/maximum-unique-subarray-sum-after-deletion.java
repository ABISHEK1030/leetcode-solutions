class Solution {
    public int maxSum(int[] nums) {
      int mx = Arrays.stream(nums).max().orElse(Integer.MIN_VALUE);
       
        if (mx <= 0) {
            return mx;
        }
       
        Set<Integer> seen = new HashSet<>();
        int ans = 0;
        for (int x : nums) {
            if (x > 0 && !seen.contains(x)) {
                ans += x;
                seen.add(x);
            }
        }
        return ans;     
    }
}