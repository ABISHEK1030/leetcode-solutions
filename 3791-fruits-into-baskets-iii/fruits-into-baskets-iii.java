class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
     int n = baskets.length;
        SegmentTree st = new SegmentTree(baskets);
        int unplaced = 0;

        for (int fruit : fruits) {
            int pos = st.query(1, 1, n, fruit);
            if (pos == -1) {
                unplaced++;
            } else {
                st.modify(1, 1, n, pos, 0);
            }
        }
        return unplaced;
    }

  
    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[n * 4];
            build(1, 1, n, nums);
        }

        void build(int idx, int l, int r, int[] nums) {
            if (l == r) {
                tree[idx] = nums[l - 1];
                return;
            }
            int mid = (l + r) >> 1;
            build(idx << 1, l, mid, nums);
            build(idx << 1 | 1, mid + 1, r, nums);
            tree[idx] = Math.max(tree[idx << 1], tree[idx << 1 | 1]);
        }

        void modify(int idx, int l, int r, int pos, int val) {
            if (l == r) {
                tree[idx] = val;
                return;
            }
            int mid = (l + r) >> 1;
            if (pos <= mid) modify(idx << 1, l, mid, pos, val);
            else modify(idx << 1 | 1, mid + 1, r, pos, val);
            tree[idx] = Math.max(tree[idx << 1], tree[idx << 1 | 1]);
        }

        int query(int idx, int l, int r, int val) {
            if (tree[idx] < val) return -1;
            if (l == r) return l;
            int mid = (l + r) >> 1;
            if (tree[idx << 1] >= val) return query(idx << 1, l, mid, val);
            else return query(idx << 1 | 1, mid + 1, r, val);
        }   
    }
}