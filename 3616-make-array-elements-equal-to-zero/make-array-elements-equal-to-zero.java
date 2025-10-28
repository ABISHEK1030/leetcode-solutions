class Solution {
    public int countValidSelections(int[] nums) {
      int totalSum = 0;
        for (int x : nums) {
            totalSum += x;
        }

        int ans = 0;
        int leftSum = 0;
        for (int x : nums) {
            if (x != 0) {
                leftSum += x;
            } else {
            
                if (2 * leftSum == totalSum) {
                    
                    ans += 2;
                } else if (Math.abs(2 * leftSum - totalSum) == 1) {
                    
                    ans += 1;
                }
            }
        }

        return ans;   
    }
}