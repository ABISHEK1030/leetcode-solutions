class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = baskets.length;
        SegmentTree st = new SegmentTree(baskets);
        int unplaced = 0;
        for (int f : fruits) {
            int pos = st.query(1, 1, n, f);
            if (pos < 0) {
                unplaced++;
            } else {
                st.modify(1, 1, n, pos, 0);
            }
        }
        return unplaced;
    }

   
    class SegmentTree {
        int[] tr;
        int n;
        public SegmentTree(int[] arr) {
            n = arr.length;
            tr = new int[n * 4];
            build(arr, 1, 1, n);
        }
        private void build(int[] a, int u, int l, int r) {
            if (l == r) {
                tr[u] = a[l - 1];
            } else {
                int m = (l + r) >> 1;
                build(a, u << 1, l, m);
                build(a, u << 1 | 1, m + 1, r);
                tr[u] = Math.max(tr[u << 1], tr[u << 1 | 1]);
            }
        }
        public int query(int u, int l, int r, int v) {
            if (tr[u] < v) return -1;
            if (l == r) return l;
            int m = (l + r) >> 1;
            if (tr[u << 1] >= v) {
                return query(u << 1, l, m, v);
            } else {
                return query(u << 1 | 1, m + 1, r, v);
            }
        }
        public void modify(int u, int l, int r, int idx, int val) {
            if (l == r) {
                tr[u] = val;
            } else {
                int m = (l + r) >> 1;
                if (idx <= m) modify(u << 1, l, m, idx, val);
                else modify(u << 1 | 1, m + 1, r, idx, val);
                tr[u] = Math.max(tr[u << 1], tr[u << 1 | 1]);
            }
        }
    }
}
