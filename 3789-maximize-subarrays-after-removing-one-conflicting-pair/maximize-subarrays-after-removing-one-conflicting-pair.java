class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
     int m = conflictingPairs.length;
        
        List<int[]>[] byL = new List[n + 2];
        for (int i = 1; i <= n + 1; i++) {
            byL[i] = new ArrayList<>();
        }
        for (int cid = 0; cid < m; cid++) {
            int a = conflictingPairs[cid][0], b = conflictingPairs[cid][1];
            int L = Math.min(a, b), R = Math.max(a, b);
            byL[L].add(new int[]{R, cid});
        }
        
        long base = 0;
        int[] firstR = new int[n + 3], firstId = new int[n + 3], secondR = new int[n + 3];
     
        firstR[n + 1] = n + 1;
        firstId[n + 1] = -1;
        secondR[n + 1] = n + 1;
        
        for (int i = n; i >= 1; i--) {
       
            int fr = firstR[i + 1], fid = firstId[i + 1], sr = secondR[i + 1];
            
            for (int[] rc : byL[i]) {
                int r = rc[0], cid = rc[1];
                if (r < fr) {
                    sr = fr;
                    fr = r;
                    fid = cid;
                } else if (r < sr) {
                    sr = r;
                }
            }
            firstR[i] = fr;
            firstId[i] = fid;
            secondR[i] = sr;
           
            base += (long)(fr - i);
        }
       
        long[] gain = new long[m];
        for (int i = 1; i <= n; i++) {
            int cid = firstId[i];
            if (cid >= 0) {
                gain[cid] += (secondR[i] - firstR[i]);
            }
        }
      
        long best = base;
        for (long g : gain) best = Math.max(best, base + g);
        return best;    
    }
}