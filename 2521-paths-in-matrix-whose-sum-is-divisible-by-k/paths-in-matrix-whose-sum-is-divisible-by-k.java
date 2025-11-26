class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][][] dp = new int[m][n][k];

        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) continue;
                int val = grid[i][j] % k;

              
                if (i > 0) {
                    for (int r = 0; r < k; ++r) {
                        int cnt = dp[i-1][j][r];
                        if (cnt != 0) {
                            int nr = (r + val) % k;
                            dp[i][j][nr] = (int)((dp[i][j][nr] + (long)cnt) % MOD);
                        }
                    }
                }

                
                if (j > 0) {
                    for (int r = 0; r < k; ++r) {
                        int cnt = dp[i][j-1][r];
                        if (cnt != 0) {
                            int nr = (r + val) % k;
                            dp[i][j][nr] = (int)((dp[i][j][nr] + (long)cnt) % MOD);
                        }
                    }
                }
            }
        }

        return dp[m-1][n-1][0];
    }
}
