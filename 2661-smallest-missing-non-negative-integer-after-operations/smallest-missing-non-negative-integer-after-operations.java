class Solution {
    public int findSmallestInteger(int[] nums, int value) {
      int n = nums.length;
        int[] count = new int[value];
        
        for (int num : nums) {
            int mod = ((num % value) + value) % value;  
            count[mod]++;
        }
        
        int ans = 0;
        while (true) {
            int mod = ans % value;
            if (count[mod] == 0) return ans;
            count[mod]--;
            ans++;
        }   
    }
}