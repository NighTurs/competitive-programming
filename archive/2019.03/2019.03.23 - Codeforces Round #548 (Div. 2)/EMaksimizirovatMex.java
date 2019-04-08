package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class EMaksimizirovatMex {

    int MAXP = 5001;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] p = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt() - 1;
        }

        BiVertex[] vs = BiVertex.emptyGraph(MAXP);
        int d = in.nextInt();
        int[] k = new int[n];
        boolean[] del = new boolean[n];
        for (int i = 0; i < d; i++) {
            k[i] = in.nextInt() - 1;
            del[k[i]] = true;
        }

        for (int i = 0; i < n; i++) {
            if (del[i]) {
                continue;
            }
            vs[p[i]].adj.add(c[i]);
        }

        BiKuhn kuhn = new BiKuhn(MAXP, m, vs);

        int notTaken = 0;

        int[] ans = new int[d];
        for (int i = d - 1; i >= 0; i--) {
            notTaken = kuhn.kuhn(notTaken);
            ans[i] = notTaken;
            int pp = p[k[i]];
            int cc = c[k[i]];
            vs[pp].adj.add(cc);
        }
        for (int i = 0; i < d; i++) {
            out.println(ans[i]);
        }
    }

    public static class BiKuhn {

        boolean[] used;
        int[] mt;
        BiVertex[] vs;

        public BiKuhn(int nu, int nv, BiVertex[] vs) {
            this.used = new boolean[nu];
            this.mt = new int[nv];
            Arrays.fill(mt, -1);
            this.vs = vs;
        }

        public int kuhn(int notTaken) {

            for (int i = notTaken; i < used.length; i++) {
                if (vs[i].adj.isEmpty()) {
                    return i;
                }
                Arrays.fill(used, false);
                if (!tryKuhn(vs[i])) {
                    return i;
                }
            }
            return used.length;
        }

        public boolean tryKuhn(BiVertex v) {
            if (used[v.idx]) {
                return false;
            }
            used[v.idx] = true;
            for (Integer to : v.adj) {
                if (mt[to] == -1 || tryKuhn(vs[mt[to]])) {
                    mt[to] = v.idx;
                    return true;
                }
            }
            return false;
        }
    }

}
