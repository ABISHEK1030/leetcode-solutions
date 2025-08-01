class Solution {
    public int maximumLength(int[] nums, int k) {
        int[][] f = new int[k][k];
        int ans = 0;

        for (int num : nums) {
            int x = num % k;
            for (int j = 0; j < k; ++j) {
                int y = (j - x + k) % k;
                f[x][y] = f[y][x] + 1;
                ans = Math.max(ans, f[x][y]);
            }
        }

        return ans;
    }
}