package task;

import java.util.Arrays;

public class BiKuhn {

    boolean[] used;
    int[] mt;
    BiVertex[] vs;

    public BiKuhn(int nu, int nv, BiVertex[] vs) {
        this.used = new boolean[nu];
        this.mt = new int[nv];
        this.vs = vs;
    }

    public void kuhn() {
        Arrays.fill(mt, -1);

        for (BiVertex v : vs) {
            if (v.adj.isEmpty()) {
                continue;
            }
            Arrays.fill(used, false);
            tryKuhn(v);
        }
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
