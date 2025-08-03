class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxFruits = 0;
        int left = 0, sum = 0;

        for (int right = 0; right < n; right++) {
            sum += fruits[right][1];
            
            while (left <= right && !isValid(fruits[left][0], fruits[right][0], startPos, k)) {
                sum -= fruits[left][1];
                left++;
            }
            maxFruits = Math.max(maxFruits, sum);
        }
        return maxFruits;
    }

    private boolean isValid(int leftPos, int rightPos, int startPos, int k) {
        int option1 = Math.abs(startPos - leftPos) + (rightPos - leftPos);
        int option2 = Math.abs(startPos - rightPos) + (rightPos - leftPos);
        return Math.min(option1, option2) <= k;
    }
}