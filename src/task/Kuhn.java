package task;

import java.util.Arrays;
import java.util.List;

public class Kuhn {

    public static void kuhn(List<List<Integer>> edges, int[] mt, boolean[] used) {
        Arrays.fill(mt, -1);

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).isEmpty()) {
                continue;
            }
            Arrays.fill(used, false);
            tryKuhn(i, edges, mt, used);
        }
    }

    public static boolean tryKuhn(int v, List<List<Integer>> edges, int[] mt, boolean[] used) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        for (Integer to : edges.get(v)) {
            if (mt[to] == -1 || tryKuhn(mt[to], edges, mt, used)) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }
}
