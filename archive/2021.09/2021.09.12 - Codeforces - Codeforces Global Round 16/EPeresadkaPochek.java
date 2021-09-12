package task;

import java.io.PrintWriter;

public class EPeresadkaPochek {
    public Res doit(Vertex cur, Vertex ignore) {

        boolean found = false;
        boolean nonLeave = false;
        int cmp = 0;
        int leaves = 0;
        for (Vertex v : cur.adj) {
            //noinspection ObjectEquality
            if (v == ignore) {
                continue;
            }
            Res res = doit(v, cur);
            if (res.type != Type.NONE) {
                found = true;
            }
            if (res.type == Type.FULL) {
                nonLeave = true;
            }
            cmp += res.cmp;
            leaves += res.leaves;
        }
        if (!found) {
            return new Res(Type.LEAVE, cmp, cur.idx == 0 ? leaves : leaves + 1);
        }
        if (!nonLeave) {
            return new Res(Type.NONE, cmp + 1, leaves);
        }
        return new Res(Type.FULL, cmp, leaves);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            vs[from].adj.add(vs[to]);
            vs[to].adj.add(vs[from]);
        }
        Res res = doit(vs[0], null);
        out.println(res.leaves - res.cmp + 1);
    }

    public static class Res {
        Type type;
        int cmp;
        int leaves;

        public Res(Type type, int cmp, int leaves) {
            this.type = type;
            this.cmp = cmp;
            this.leaves = leaves;
        }
    }

    public static enum Type {
        NONE,
        LEAVE,
        FULL
    }
}
