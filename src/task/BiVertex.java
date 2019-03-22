package task;

import java.util.ArrayList;
import java.util.List;

public class BiVertex {
    int idx;
    List<Integer> adj = new ArrayList<>();

    public static BiVertex[] emptyGraph(int n) {
        BiVertex[] vs = new BiVertex[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new BiVertex();
            vs[i].idx = i;
        }
        return vs;
    }
}
