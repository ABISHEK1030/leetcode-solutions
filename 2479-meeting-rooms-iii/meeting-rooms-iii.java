class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));


        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

        
        PriorityQueue<long[]> occupiedRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]);
        });

        
        int[] meetingCounts = new int[n];

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            
            while (!occupiedRooms.isEmpty() && occupiedRooms.peek()[0] <= start) {
                availableRooms.offer((int) occupiedRooms.poll()[1]);
            }

            if (!availableRooms.isEmpty()) {
               
                int room = availableRooms.poll();
                occupiedRooms.offer(new long[]{end, room});
                meetingCounts[room]++;
            } else {
               
                long[] earliest = occupiedRooms.poll();
                long newEnd = earliest[0] + (end - start);
                int room = (int) earliest[1];
                occupiedRooms.offer(new long[]{newEnd, room});
                meetingCounts[room]++;
            }
        }

        
        int maxMeetings = 0, resultRoom = 0;
        for (int i = 0; i < n; i++) {
            if (meetingCounts[i] > maxMeetings) {
                maxMeetings = meetingCounts[i];
                resultRoom = i;
            }
        }

        return resultRoom;

        
    }
}