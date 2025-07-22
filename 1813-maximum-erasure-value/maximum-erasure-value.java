class Solution {
    public int maximumUniqueSubarray(int[] nums) {
      int n = nums.length;
       
        int[] last = new int[10001];
        int[] prefix = new int[n + 1];
        
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        int maxSum = 0, start = 0;
        for (int i = 1; i <= n; i++) {
            int v = nums[i - 1];
           
            start = Math.max(start, last[v]);
            
            maxSum = Math.max(maxSum, prefix[i] - prefix[start]);
           
            last[v] = i;
        }
        return maxSum;  
    }
}