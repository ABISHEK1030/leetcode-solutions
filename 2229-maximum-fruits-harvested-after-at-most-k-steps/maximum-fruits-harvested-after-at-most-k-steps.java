class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int ans = 0, sum = 0;
        int i = 0;
        for (int j = 0; j < fruits.length; ++j) {
            int pj = fruits[j][0];
            int fj = fruits[j][1];
            sum += fj;
           
            while (i <= j && 
                   pj - fruits[i][0] 
                   + Math.min(Math.abs(startPos - fruits[i][0]), Math.abs(startPos - pj)) 
                   > k) {
                sum -= fruits[i][1];
                i++;
            }
            ans = Math.max(ans, sum);
        }
        return ans; 
    }
}