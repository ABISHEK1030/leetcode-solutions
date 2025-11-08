class Solution {
    public int minimumOneBitOperations(int n) {
       return helper(n);
    }

    private int helper(int n) {
        if (n == 0) return 0;
        int k = 0;
        while ((1 << (k + 1)) <= n) k++;
        return (1 << (k + 1)) - 1 - helper(n ^ (1 << k));   
    }
}