class Solution {
    public List<String> removeSubfolders(String[] folder) {
     Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        String prev = "";

        for (String f : folder) {
            
            if (!prev.isEmpty() && f.startsWith(prev) && f.charAt(prev.length()) == '/') {
                continue;
            }
           
            result.add(f);
            prev = f;
        }

        return result;    
    }
}