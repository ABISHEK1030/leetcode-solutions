class Solution {
    public int countPalindromicSubsequence(String s) {
       int n = s.length();
        int ans = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);
            if (first == -1 || first == last) continue;
            boolean[] seen = new boolean[26];
            for (int i = first + 1; i < last; i++) {
                seen[s.charAt(i) - 'a'] = true;
            }
            for (int k = 0; k < 26; k++) if (seen[k]) ans++;
        }
        return ans;   
    }
}