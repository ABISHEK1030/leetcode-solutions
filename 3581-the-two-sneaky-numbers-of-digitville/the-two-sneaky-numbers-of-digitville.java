class Solution {
    public int[] getSneakyNumbers(int[] nums) {
         final int kMax = 100;  
        int[] ans = new int[2];
        int[] count = new int[kMax + 1];
        int idx = 0;

        for (int num : nums) {
            count[num]++;
            if (count[num] == 2) {
                ans[idx++] = num;
                if (idx == 2) {
                    break;
                }
            }
        }
        return ans;  
    }
}