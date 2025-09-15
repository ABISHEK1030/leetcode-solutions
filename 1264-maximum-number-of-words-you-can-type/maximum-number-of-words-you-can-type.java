class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
      boolean[] broken = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;     
        }

        int count = 0;
        for (String word : text.split(" ")) { 
            boolean valid = true;
            for (char ch : word.toCharArray()) {
                if (broken[ch - 'a']) {      
                    valid = false;
                    break;
                }
            }
            if (valid) count++;             
        }
        return count;   
    }
}