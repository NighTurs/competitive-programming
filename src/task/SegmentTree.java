package task;

public class SegmentTree {

    public Data[] t;

    public SegmentTree(int n) {
        this.t = new Data[n * 4 + 1];
        for (int i = 0; i < n * 4 + 1; i++) {
            this.t[i] = new Data();
        }
    }

    public void build(int a[], int v, int tl, int tr) {
        if (tl == tr) {
            t[v].update(a[tl]);
        } else {
            int tm = (tl + tr) / 2;
            build(a, v * 2, tl, tm);
            build(a, v * 2 + 1, tm + 1, tr);
            t[v].combine(t[v * 2], t[v * 2 + 1]);
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

    public Data query(int v, int tl, int tr, int l, int r) {
        if (l == tl && tr == r) {
            return t[v];
        }
        int tm = (tl + tr) / 2;
        if (r <= tm) {
            return query(v * 2, tl, tm, l, r);
        }
        if (l > tm) {
            return query(v * 2 + 1, tm + 1, tr, l, r);
        }
        return new Data().combine(query(v * 2, tl, tm, l, tm), query(v * 2 + 1, tm + 1, tr, tm + 1, r));
    }

    public static class Data {
        int sum;

        public void update(int val) {
            this.sum = val;
        }

        public Data combine(Data a, Data b) {
            this.sum = a.sum + b.sum;
            return this;
        }
    }
}
