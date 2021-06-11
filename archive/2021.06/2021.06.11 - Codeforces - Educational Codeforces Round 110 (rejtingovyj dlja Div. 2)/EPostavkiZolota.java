package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EPostavkiZolota {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int q = in.nextInt();
        int a0 = in.nextInt();
        int c0 = in.nextInt();
        List<Vertex> vs = new ArrayList<>();
        Vertex v = new Vertex(0, a0, c0);
        v.up[0] = v;
        for (int i = 1; i < v.up.length; ++i) {
            v.up[i] = v.up[i - 1].up[i - 1];
        }
        vs.add(v);
        Map<Integer, Integer> vsMap = new HashMap<>();
        vsMap.put(0, 0);

        for (int qq = 0; qq < q; qq++) {
            int type = in.nextInt();
            if (type == 1) {
                int p = in.nextInt();
                int a = in.nextInt();
                int c = in.nextInt();
                vsMap.put(qq + 1, vs.size());
                v = new Vertex(qq + 1, a, c);
                vs.add(v);
                v.up[0] = vs.get(vsMap.get(p));
                for (int i = 1; i < v.up.length; ++i) {
                    v.up[i] = v.up[i - 1].up[i - 1];
                }
            } else {
                int vv = in.nextInt();
                int w = in.nextInt();
                long ct = 0;
                long cost = 0;
                Vertex first = vs.get(vsMap.get(vv));
                while (w > 0 && first.a > 0) {
                    Vertex cur = vs.get(vsMap.get(vv));
                    for (int i = cur.up.length - 1; i >= 0; --i) {
                        if (cur.a == 0) {
                            break;
                        }
                        if (cur.up[i].a > 0) {
                            cur = cur.up[i];
                        }
                    }
                    long take = Math.min(w, cur.a);
                    ct += take;
                    w -= take;
                    cur.a -= take;
                    cost += take * cur.c;
                }
                out.println(ct + " " + cost);
                out.flush();
            }
        }
    }

    public static class Vertex {
        int idx;
        int a;
        int c;
        Vertex[] up;

        public Vertex(int idx, int a, int c) {
            this.idx = idx;
            this.a = a;
            this.c = c;
            this.up = new Vertex[20];
        }
    }
}
