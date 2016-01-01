package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskE {
    int N = (int) 1e6;
    NODE[] tree = new NODE[N * 4];

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        buildTree(s, 1, 0, s.length() - 1);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int l = in.nextInt() - 1, r = in.nextInt() - 1;
            out.println(solveTree(1, 0, s.length() - 1, l, r).size * 2);
        }
    }

    public void buildTree(String s, int p, int l, int r) {
        if (l == r) {
            tree[p] = new NODE();
            tree[p].size = 0;
            tree[p].l = s.charAt(l) == '(' ? 1 : 0;
            tree[p].r = s.charAt(l) == ')' ? 1 : 0;
            return;
        }

        int m = (l + r) / 2;

        buildTree(s, p * 2, l, m);
        buildTree(s, p * 2 + 1, m + 1, r);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    public NODE solveTree(int p, int l, int r, int ll, int rr) {
        if (ll > rr) {
            return new NODE();
        }

        if (l == ll && r == rr) {
            NODE node = new NODE();
            node.l = tree[p].l;
            node.r = tree[p].r;
            node.size = tree[p].size;
            return node;
        }

        int m = (l + r) / 2;
        return merge(solveTree(p * 2, l, m, ll, Math.min(m, rr)),
                solveTree(p * 2 + 1, m + 1, r, Math.max(m + 1, ll), rr));
    }

    public NODE merge(NODE a, NODE b) {
        NODE n = new NODE();
        int d = Math.min(a.l, b.r);
        n.size = a.size + b.size + d;
        n.l = a.l + b.l - d;
        n.r = a.r + b.r - d;
        return n;
    }

    public static class NODE {
        int size;
        int l, r;
    }
}
