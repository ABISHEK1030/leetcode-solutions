class Solution {
    public int maxFrequencyElements(int[] nums) {
         Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
   
        int maxFreq = 0;
        for (int f : freqMap.values()) {
            if (f > maxFreq) {
                maxFreq = f;
            }
        }
 
        int sum = 0;
        for (int f : freqMap.values()) {
            if (f == maxFreq) {
                sum += f;
            }
        }
        
        return sum; 
    }
}