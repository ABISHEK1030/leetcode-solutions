class Solution {
    public long flowerGame(int n, int m) {
      long oddX = (n + 1L) / 2;
        long evenX = n / 2L;
        long oddY = (m + 1L) / 2;
        long evenY = m / 2L;
        return oddX * evenY + evenX * oddY;   
    }
}