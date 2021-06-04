package task;

import java.io.PrintWriter;

public class F2UgadaiKINolSlozhnayaVersiya {
    static PrintWriter out;
    static InputReader in;
    static int n;
    static boolean found = false;

    public void solve(int testNumber, InputReader in_, PrintWriter out_) {
        in = in_;
        out = out_;
        n = in.nextInt();
        int t = in.nextInt();
        SegmentTree sg = new SegmentTree(n);

        for (int i = 0; i < t; i++) {
            int p = in.nextInt();
            found = false;
            sg.query(1, 0, n - 1, 0, p);
        }
    }

    public static class SegmentTree {

        public Data[] t;

        public SegmentTree(int n) {
            this.t = new Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new Data();
            }
        }

        public void update(int v, int tl, int tr, int pos, int newVal) {
            if (tl == tr) {
                t[v].update(newVal);
            } else {
                int tm = (tl + tr) / 2;
                if (pos <= tm) {
                    update(v * 2, tl, tm, pos, newVal);
                } else {
                    update(v * 2 + 1, tm + 1, tr, pos, newVal);
                }
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public int query(int v, int tl, int tr, int tail, int q) {
            if (t[v].sum == -1) {
                out.println("? " + (tl + 1) + " " + (tr + 1));
                out.flush();
                int ans = in.nextInt();
                if (ans == -1) {
                    System.exit(0);
                }
                t[v].sum = ans;
            }

            int zeroes = tr + 1 - (t[v].sum + tail);
            if (zeroes < q) {
                return t[v].sum;
            }

            if (tl == tr) {
                if (tr + 1 - (t[v].sum + tail) == q) {
                    out.println("! " + (tl + 1));
                    out.flush();
                    update(1, 0, n - 1, tl, 0);
                    found = true;
                }
                return t[v].sum;
            }


            int tm = (tl + tr) / 2;
            int l = query(v * 2, tl, tm, tail, q);
            if (found) {
                return 0;
            }
            t[v * 2 + 1].sum = t[v].sum - l;
            int r = query(v * 2 + 1, tm + 1, tr, tail + l, q);
            if (found) {
                return 0;
            }
            return t[v].sum;
        }

        public static class Data {

            int sum = -1;

            public void update(int val) {
                this.sum = val;
            }

            public Data combine(Data a, Data b) {
                this.sum = a.sum + b.sum;
                return this;
            }
        }
    }

}
