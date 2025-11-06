import java.util.*;

class Solution {
    int[] parent, size;
    Map<Integer, TreeSet<Integer>> compOnline;

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        parent = new int[c + 1];
        size = new int[c + 1];
        for (int i = 1; i <= c; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int[] conn : connections) {
            union(conn[0], conn[1]);
        }

        compOnline = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            int root = find(i);
            compOnline.computeIfAbsent(root, k -> new TreeSet<>()).add(i);
        }

        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0], x = q[1];
            if (type == 2) { 
                if (online[x]) {
                    online[x] = false;
                    int root = find(x);
                    compOnline.get(root).remove(x);
                }
            } else { // Query
                if (online[x]) {
                    ans.add(x);
                } else {
                    int root = find(x);
                    TreeSet<Integer> ts = compOnline.get(root);
                    if (ts == null || ts.isEmpty()) ans.add(-1);
                    else ans.add(ts.first());
                }
            }
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
        }
    }
}