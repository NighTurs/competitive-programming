package task;

import java.util.ArrayList;
import java.util.List;

public class BinaryLifting {

    Vertex[] vs;
    int timer;

    public BinaryLifting(Vertex[] vs, Vertex head) {
        this.vs = vs;
        this.timer = 1;
        dfs(head, head);
    }

    public void dfs(Vertex v, Vertex p) {
        v.tin = ++timer;
        v.up[0] = p;
        for (int i = 1; i < v.up.length; ++i) {
            v.up[i] = v.up[i - 1].up[i - 1];
        }
        for (Vertex to : v.adj) {
            //noinspection ObjectEquality
            if (to != p) {
                dfs(to, v);
            }
        }
        v.tout = ++timer;
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    public Vertex lca(Vertex a, Vertex b) {
        if (a.upper(b)) {
            return a;
        }
        if (b.upper(a)) {
            return b;
        }
        for (int i = a.up.length - 1; i >= 0; --i) {
            if (!a.up[i].upper(b)) {
                a = a.up[i];
            }
        }
        return a.up[0];
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    public Vertex ithParent(Vertex a, int i) {
        while (i != 0) {
            int pow = a.up.length - 1;
            while ((1 << pow) > i) {
                pow--;
            }
            a = a.up[pow];
            i -= (1 << pow);
        }
        return a;
    }

    public static class Vertex {

        int idx;
        int tin;
        int tout;
        List<Vertex> adj = new ArrayList<>();
        Vertex[] up;

        public static Vertex[] emptyGraph(int n) {
            int l = 1;
            while ((1 << l) <= n) {
                ++l;
            }
            Vertex[] vs = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vs[i] = new Vertex();
                vs[i].idx = i;
                vs[i].up = new Vertex[l + 1];
            }
            return vs;
        }

        boolean upper(Vertex b) {
            return tin <= b.tin && tout >= b.tout;
        }
    }
}
