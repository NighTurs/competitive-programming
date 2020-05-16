package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityUpdate {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] x = new int[n];
        for (int i = 1; i < n; i++) {
            x[i] = in.nextInt();
        }

        Vertex[] vs = Vertex.emptyGraph(n);

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            vs[a].adj.add(vs[b]);
            vs[a].ei.add(i);
            vs[b].adj.add(vs[a]);
            vs[b].ei.add(i);
        }

        int[] ans = new int[m];
        int[] visitT = new int[n];
        Arrays.fill(visitT, -1);
        visitT[0] = 0;

        int t = 0;
        int count = 1;
        while (count < n) {
            int found = 0;
            int minT = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                if (x[i] > t && minT > x[i]) {
                    minT = x[i];
                }
                if (x[i] != -count) {
                    continue;
                }
                found++;
                visitT[i] = t + 1;
                for (int h = 0; h < vs[i].adj.size(); h++) {
                    int from = vs[i].adj.get(h).idx;
                    if (visitT[from] != -1 && visitT[from] <= t) {
                        ans[vs[i].ei.get(h)] = t + 1 - visitT[from];
                        break;
                    }
                }
            }
            if (found > 0) {
                count += found;
                t = t + 1;
                minT = t;
            }
            t = minT;
            for (int i = 1; i < n; i++) {
                if (x[i] != minT) {
                    continue;
                }
                count++;
                visitT[i] = minT;
                for (int h = 0; h < vs[i].adj.size(); h++) {
                    int from = vs[i].adj.get(h).idx;
                    if (visitT[from] != -1 && visitT[from] < t) {
                        ans[vs[i].ei.get(h)] = t - visitT[from];
                        break;
                    }
                }
            }
        }

        out.print("Case #" + testNumber + ": ");
        for (int i = 0; i < m; i++) {
            out.print(ans[i] != 0 ? ans[i] : (int) 1e6);
            out.print(" ");
        }
        out.println();

    }

    public static class Vertex {
        int idx;
        List<Vertex> adj = new ArrayList<>();
        List<Integer> ei = new ArrayList<>();

        public static Vertex[] emptyGraph(int n) {
            Vertex[] vs = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vs[i] = new Vertex();
                vs[i].idx = i;
            }
            return vs;
        }
    }

}
