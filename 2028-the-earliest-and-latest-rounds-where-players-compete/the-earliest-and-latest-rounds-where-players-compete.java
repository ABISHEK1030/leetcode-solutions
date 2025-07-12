class Solution {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
         int[][][][] mem = new int[n + 1][n + 1][n + 1][2];
        return solve(firstPlayer, n - secondPlayer + 1, n, mem);
    }

    // Returns [earliest, latest] for players at positions l (from front) and r (from back) in k players
    private int[] solve(int l, int r, int k, int[][][][] mem) {
        if (l == r) return new int[]{1, 1};
        if (l > r) return solve(r, l, k, mem);
        if (mem[l][r][k][0] != 0) return mem[l][r][k];

        int earliest = Integer.MAX_VALUE, latest = Integer.MIN_VALUE;
        int half = (k + 1) / 2;

        for (int i = 1; i <= l; ++i) {
            for (int j = l - i + 1; j <= r - i; ++j) {
                // Check if positions i and j meet in this round
                if (i + j > half || i + j < l + r - k / 2) continue;

                int[] sub = solve(i, j, half, mem);
                earliest = Math.min(earliest, sub[0] + 1);
                latest = Math.max(latest, sub[1] + 1);
            }
        }

        mem[l][r][k][0] = earliest;
        mem[l][r][k][1] = latest;
        return new int[]{earliest, latest};
    
        
    }
}