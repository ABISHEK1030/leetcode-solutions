class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
      int m = languages.length;
        List<Set<Integer>> langList = new ArrayList<>();
    
        for (int[] lang : languages) {
            Set<Integer> set = new HashSet<>();
            for (int l : lang) set.add(l);
            langList.add(set);
        }
        Set<Integer> needTeach = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1;
            if (!canCommunicate(langList.get(u), langList.get(v))) {
                needTeach.add(u);
                needTeach.add(v);
            }
        }

        if (needTeach.isEmpty()) return 0;

       
        int[] count = new int[n + 1];
        for (int person : needTeach) {
            for (int l : langList.get(person)) {
                count[l]++;
            }
        }

       
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, count[i]);
        }

        return needTeach.size() - max;
    }

    private boolean canCommunicate(Set<Integer> a, Set<Integer> b) {
        for (int lang : a) {
            if (b.contains(lang)) return true;
        }
        return false;   
    }
}