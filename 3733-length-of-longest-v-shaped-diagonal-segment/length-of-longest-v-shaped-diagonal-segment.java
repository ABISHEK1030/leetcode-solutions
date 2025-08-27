class Solution {
    private int m, n;
    private Integer[][][][][] memo;
   
    private final int[][] dirs = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

    public int lenOfVDiagonal(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        memo = new Integer[m][n][2][2][4]; 
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue; 
                for (int d = 0; d < 4; d++) {
                    result = Math.max(result,
                        1 + dfs(grid, i + dirs[d][0], j + dirs[d][1], false, 0, d));
                }
            }
        }
        return result;
    }

    private int dfs(int[][] grid, int i, int j, boolean turned, int nextIdx, int dir) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;
        int expected = (nextIdx == 0 ? 2 : 0);
        if (grid[i][j] != expected) return 0;

        int t = turned ? 1 : 0;
        if (memo[i][j][t][nextIdx][dir] != null)
            return memo[i][j][t][nextIdx][dir];

        int nextNextIdx = (expected == 2 ? 1 : 0);
        int best = 1 + dfs(grid, i + dirs[dir][0], j + dirs[dir][1], turned, nextNextIdx, dir);

       
        if (!turned) {
            int newDir = (dir + 1) % 4; 
            best = Math.max(best, 1 + dfs(grid, i + dirs[newDir][0], j + dirs[newDir][1],
                                          true, nextNextIdx, newDir));
        }

        return memo[i][j][t][nextIdx][dir] = best;
    }
}
