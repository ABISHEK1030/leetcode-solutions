class Solution {
    public int maxSumDivThree(int[] nums) {
      int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = dp[2] = Integer.MIN_VALUE; 

        for (int num : nums) {
            int[] next = dp.clone();
            int rem = num % 3;
            for (int r = 0; r < 3; r++) {
                if (dp[r] != Integer.MIN_VALUE) {
                    int newRem = (r + rem) % 3;
                    next[newRem] = Math.max(next[newRem], dp[r] + num);
                }
            }
            dp = next;
        }

        return Math.max(0, dp[0]);   
    }
}