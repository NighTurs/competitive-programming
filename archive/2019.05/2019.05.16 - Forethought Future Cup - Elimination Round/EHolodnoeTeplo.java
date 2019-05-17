package task;

import java.io.PrintWriter;

public class EHolodnoeTeplo {

    static final int MAX = (int) 1e5 + 10;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        SegmentTree tree = new SegmentTree(MAX + 1);
        for (int i = 0; i < q; i++) {
            String s = in.next();
            int x = in.nextInt();
            if (s.equals(">")) {
                if (x < -1) {
                    tree.update(1, 0, MAX, 0, Math.abs(x) - 1, State.SWAP);
                    tree.update(1, 0, MAX, Math.abs(x), MAX, State.NEGATIVE);
                } else {
                    tree.update(1, 0, MAX, x + 1, MAX, State.NEGATIVE);
                }
            } else {
                if (x > 1) {
                    tree.update(1, 0, MAX, 0, x - 1, State.SWAP);
                    tree.update(1, 0, MAX, x, MAX, State.POSITIVE);
                } else {
                    tree.update(1, 0, MAX, Math.abs(x - 1), MAX, State.POSITIVE);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            State st = tree.query(1, 0, MAX, Math.abs(a[i]));
            if (st == State.POSITIVE) {
                out.print(Math.abs(a[i]));
            } else if (st == State.NEGATIVE) {
                out.print(-Math.abs(a[i]));
            } else if (st == State.NORM) {
                out.print(a[i]);
            } else if (st == State.SWAP){
                out.print(-a[i]);
            } else {
                throw new RuntimeException("unknown");
            }
            out.print(' ');
        }
    }

    public static class SegmentTree {

        public State[] t;

        public SegmentTree(int n) {
            this.t = new State[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = State.NORM;
            }
        }

        public void update(int v, int tl, int tr, int l, int r, State state) {
            if (l == tl && tr == r) {
                t[v] = t[v].combine(state);
                return;
            }
            push(v, tl, tr);
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                update(v * 2, tl, tm, l, r, state);
            } else if (l > tm) {
                update(v * 2 + 1, tm + 1, tr, l, r, state);
            } else {
                update(v * 2, tl, tm, l, Math.min(tm, r), state);
                update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, state);
            }
        }

        public void push(int v, int tl, int tr) {
            if (tl == tr) {
                return;
            }
            t[v * 2] = t[v * 2].combine(t[v]);
            t[v * 2 + 1] = t[v * 2 + 1].combine(t[v]);
            t[v] = State.NORM;
        }

        public State query(int v, int tl, int tr, int pos) {
            if (tl == tr) {
                return t[v];
            }
            int tm = (tl + tr) / 2;
            push(v, tl, tr);
            if (pos <= tm) {
                return query(v * 2, tl, tm, pos);
            } else {
                return query(v * 2 + 1, tm + 1, tr, pos);
            }
        }
    }

    public enum State {
        NORM,
        SWAP,
        NEGATIVE,
        POSITIVE;
        private State anti;

        static {
            NORM.anti = SWAP;
            SWAP.anti = NORM;
            NEGATIVE.anti = POSITIVE;
            POSITIVE.anti = NEGATIVE;
        }

        State combine(State state) {
            switch (state) {
                case NORM:
                    return this;
                case SWAP:
                    return this.anti;
                case NEGATIVE:
                    return NEGATIVE;
                case POSITIVE:
                    return POSITIVE;
                default:
                    throw new RuntimeException("unknown");
            }
        }
    }
}
