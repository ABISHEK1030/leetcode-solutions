class Solution {
    public int[] avoidFlood(int[] rains) {
      int n = rains.length;
        int[] res = new int[n];
        Map<Integer, Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                dryDays.add(i);
                res[i] = 1; 
            } else {
                res[i] = -1;
                if (fullLakes.containsKey(lake)) {
                    Integer dryDay = dryDays.higher(fullLakes.get(lake));
                    if (dryDay == null) return new int[0];
                    res[dryDay] = lake;
                    dryDays.remove(dryDay);
                }
                fullLakes.put(lake, i);
            }
        }
        return res;   
    }
}