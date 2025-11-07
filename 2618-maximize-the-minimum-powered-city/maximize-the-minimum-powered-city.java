class Solution {
    public long maxPower(int[] stations, int r, int k) {
      int n = stations.length;
        long[] prefix = new long[n+1];
        for (int i = 0; i < n; i++) prefix[i+1] = prefix[i] + stations[i];

        long left = 0, right = (long)1e18, ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (can(mid, stations, prefix, r, k)) {
                ans = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return ans;
    }

    private boolean can(long target, int[] stations, long[] prefix, int r, long k) {
        int n = stations.length;
        long[] added = new long[n];
        long windowAdd = 0;

        for (int i = 0; i < n; i++) {
            if (i - r - 1 >= 0) windowAdd -= added[i - r - 1];

            long currentPower = prefix[Math.min(n, i + r + 1)] - prefix[Math.max(0, i - r)] + windowAdd;

            if (currentPower < target) {
                long need = target - currentPower;
                if (need > k) return false;
                k -= need;
                added[Math.min(n - 1, i + r)] += need;
                windowAdd += need;
            }
        }
        return true;   
    }
}