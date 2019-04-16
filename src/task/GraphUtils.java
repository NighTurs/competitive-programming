package task;

import java.util.ArrayList;
import java.util.List;

public class GraphUtils {

    public static Vertex[] readEdgeGraph(InputReader in) {
        int n = in.nextInt();
        int m = in.nextInt();

        Vertex[] vs = Vertex.emptyGraph(m);

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            for (Integer to : adj.get(a)) {
                vs[i].adj.add(vs[to]);
                vs[to].adj.add(vs[i]);
            }

            for (Integer to : adj.get(b)) {
                vs[i].adj.add(vs[to]);
                vs[to].adj.add(vs[i]);
            }

            adj.get(a).add(i);
            adj.get(b).add(i);
        }

        return vs;
    }
}
