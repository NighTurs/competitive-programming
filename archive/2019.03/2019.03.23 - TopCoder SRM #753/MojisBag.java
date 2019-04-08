package task;

public class MojisBag {

    public int maximumXor(int Q, int base, int add, int rate) {
        long prev = 0;
        long MOD = (int) (1e9 + 7);

        Vertex root = new Vertex(((long) 1) << 32);
        boolean[] deleted = new boolean[Q];
        long[] xs = new long[Q];

        long max = 0;
        long x1 = 0;
        long x2 = 0;

        long z = 0;
        int added = 0;
        for (int i = 0; i < Q; i++) {
            long x = add;
            if (i != 0) {
                x = (prev * base + add) % MOD;
            }

            prev = x;
            if (x % rate != 0) {
                add(x, 32, root);

                long cand = maxXor(x, 32, root);
                if ((cand ^ x) > max) {
                    max = cand ^ x;
                    x1 = cand;
                    x2 = x;
                }
                xs[added++] = x;
            } else if (added > 0) {
                int pos = (int) (x % added);
                if (!deleted[pos]) {
                    deleted[pos] = true;
                    int left = delete(xs[pos], 32, root);
                    if (left == 0 && (xs[pos] == x1 || xs[pos] == x2)) {
                        Pair<Long, Long> p = new Pair<>((long)0, (long)0);
                        fullSearch(32, root, root, p);
                        max = p.fs ^ p.sc;
                        x1 = p.fs;
                        x2 = p.sc;
                    }
                }
            }
//            System.out.println(max);
            if (i == 0) {
                z = max;
            } else {
                z = (z * base + max) % MOD;
            }
        }
        return (int)z;
    }

    public long eye(long x, int pos) {
        return x & ((long)1 << pos);
    }

    public boolean exists(Vertex vertex) {
        return vertex != null && vertex.count > 0;
    }

    public void fullSearch(int pos, Vertex left, Vertex right, Pair<Long, Long> max) {
        if (pos == 0) {
            if ((left.val ^ right.val) > (max.fs ^ max.sc)) {
                max.fs = left.val;
                max.sc = right.val;
            }

            return;
        }
//        if (eye(left.val ^ right.val, pos) < eye(max.fs ^ max.sc, pos)) {
//            return;
//        }

        boolean primary = false;
        if (exists(left.one) && exists(right.zero)) {
            primary = true;
            fullSearch(pos - 1, left.one, right.zero, max);
        }
        if (exists(left.zero) && exists(right.one)) {
            primary = true;
            fullSearch(pos - 1, left.zero, right.one, max);
        }
        if (!primary && exists(left.zero) && exists(right.zero)) {
            fullSearch(pos - 1, left.zero, right.zero, max);
        }
        if (!primary && exists(left.one) && exists(right.one)) {
            fullSearch(pos - 1, left.one, right.one, max);
        }
    }

    public long maxXor(long x, int pos, Vertex cur) {
        if (pos == 0) {
            return cur.val;
        }
        if (eye(x, pos - 1) == 0) {
            if (exists(cur.one)) {
                return maxXor(x, pos - 1, cur.one);
            } else {
                return maxXor(x, pos - 1, cur.zero);
            }
        } else {
            if (exists(cur.zero)) {
                return maxXor(x, pos - 1, cur.zero);
            } else {
                return maxXor(x, pos - 1, cur.one);
            }
        }
    }

    public void add(long x, int pos, Vertex cur) {
        cur.count++;
        if (pos == 0) {
            return;
        }
        if (eye(x, pos - 1) == 0) {
            if (cur.zero == null) {
                cur.zero = new Vertex(x);
            }
            add(x, pos - 1, cur.zero);
        } else {
            if (cur.one == null) {
                cur.one = new Vertex(x);
            }
            add(x, pos - 1, cur.one);
        }
    }

    public int delete(long x, int pos, Vertex cur) {
        assert cur.count > 0;
        cur.count--;
        if (pos == 0) {
            return cur.count;
        }
        if (eye(x, pos - 1) == 0) {
            return delete(x, pos - 1, cur.zero);
        } else {
            return delete(x, pos - 1, cur.one);
        }
    }

    public static class Vertex {

        long val = 0;
        int count = 0;
        Vertex zero;
        Vertex one;

        public Vertex(long val) {
            this.val = val;
        }
    }
}
