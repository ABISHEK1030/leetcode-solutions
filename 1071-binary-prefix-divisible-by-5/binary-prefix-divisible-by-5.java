class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
              List<Boolean> res = new ArrayList<>();
        int num = 0;

        for (int bit : nums) {
            num = (num * 2 + bit) % 5; 
            res.add(num == 0);
        }

        return res;
  
    }
}