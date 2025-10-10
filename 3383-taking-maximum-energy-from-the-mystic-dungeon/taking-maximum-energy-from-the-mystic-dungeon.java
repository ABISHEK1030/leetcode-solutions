class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        // Use a very low initial value
        int ans = Integer.MIN_VALUE;
        // Iterate over the possible ending positions: indices [n - k, n-1]
        for (int i = n - k; i < n; i++) {
            int sum = 0;
            // Move backwards in steps of k
            for (int j = i; j >= 0; j -= k) {
                sum += energy[j];
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }
}
