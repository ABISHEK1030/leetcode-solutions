class Solution {
    public int[] smallestSubarrays(int[] nums) {
       int n = nums.length;
        int[] res = new int[n];
        int[] lastSeen = new int[32]; 

        for (int i = 0; i < 32; i++) {
            lastSeen[i] = -1;
        }

        for (int i = n - 1; i >= 0; i--) {
            
            for (int bit = 0; bit < 32; bit++) {
                if (((nums[i] >> bit) & 1) == 1) {
                    lastSeen[bit] = i;
                }
            }

            int maxIndex = i;
            for (int bit = 0; bit < 32; bit++) {
                if (lastSeen[bit] != -1) {
                    maxIndex = Math.max(maxIndex, lastSeen[bit]);
                }
            }

            res[i] = maxIndex - i + 1;
        }

        return res; 
    }
}