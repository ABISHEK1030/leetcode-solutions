class Solution {
    public List<String> removeAnagrams(String[] words) {
      List<String> result = new ArrayList<>();
        String prevSorted = "";

        for (String word : words) {
            String sortedWord = sortString(word);
            if (!sortedWord.equals(prevSorted)) {
                result.add(word);
                prevSorted = sortedWord;
            }
        }

        return result;
    }

    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);    
    }
}