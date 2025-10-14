class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        for (int i = 0; i + 2 * k <= n; i++) {
            boolean first = true, second = true;
            for (int j = i + 1; j < i + k; j++) {
                if (nums.get(j) <= nums.get(j - 1)) {
                    first = false;
                    break;
                }
            }
            for (int j = i + k + 1; j < i + 2 * k; j++) {
                if (nums.get(j) <= nums.get(j - 1)) {
                    second = false;
                    break;
                }
            }
            if (first && second) return true;
        }
        return false;
    }
}
