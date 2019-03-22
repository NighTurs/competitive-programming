package task;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int idx;
    List<Vertex> adj = new ArrayList<>();

    public static Vertex[] emptyGraph(int n) {
        Vertex[] vs = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new Vertex();
            vs[i].idx = i;
        }
        return vs;
    }
}
