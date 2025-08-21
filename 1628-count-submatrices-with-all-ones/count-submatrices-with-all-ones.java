class Solution {
    public int numSubmat(int[][] mat) {
       int m = mat.length, n = mat[0].length;
        int[] h = new int[n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                h[j] = mat[i][j] == 0 ? 0 : h[j] + 1;
            }

           
            java.util.Deque<int[]> st = new java.util.ArrayDeque<>();
            int rowSum = 0;

            for (int j = 0; j < n; j++) {
                int cnt = 1;
                while (!st.isEmpty() && st.peek()[0] >= h[j]) {
                    int[] prev = st.pop();
                    rowSum -= prev[1] * (prev[0] - h[j]); 
                    cnt += prev[1];
                }
                st.push(new int[]{h[j], cnt});
                rowSum += h[j];
                ans += rowSum;
            }
        }
        return ans;  
    }
}