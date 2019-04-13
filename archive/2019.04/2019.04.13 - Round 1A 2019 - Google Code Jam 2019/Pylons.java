package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pylons {

    static final long seed = System.nanoTime();
    static final Random rand = new Random(seed);
    Pylons.Vertex[][] vs;
    boolean[][] visited;
    int n;
    int m;
    int testNumber;
    PrintWriter out;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        this.testNumber = testNumber;
        this.out = out;

        n = in.nextInt();
        m = in.nextInt();

        vs = new Pylons.Vertex[n][m];

        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                vs[i][h] = new Pylons.Vertex(i, h);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                for (int j1 = 0; j1 < n; j1++) {
                    for (int j2 = 0; j2 < m; j2++) {
                        if (i == j1 || h == j2) {
                            continue;
                        }
                        if (i + h == j1 + j2 || i - h == j1 - j2) {
                            continue;
                        }
                        vs[i][h].adj.add(vs[j1][j2]);
                    }
                }
            }
        }

        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                if (traverse(vs[i][h], 1)) {
                    out.println(String.format("%d %d", i + 1, h + 1));
                    return;
                }
            }
        }
        out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
    }

    boolean traverse(Pylons.Vertex v, int count) {
        if (count == n * m) {
            out.println(String.format("Case #%d: POSSIBLE", testNumber));
            return true;
        }
        visited[v.x][v.y] = true;
        for (Pylons.Vertex to : v.shuffleAdj()) {
            if (!visited[to.x][to.y]) {
                if (traverse(to, count + 1)) {
                    out.println(String.format("%d %d", to.x + 1, to.y + 1));
                    return true;
                }
            }
        }

        visited[v.x][v.y] = false;
        return false;
    }

    public static class Vertex {

        int x;
        int y;
        List<Pylons.Vertex> adj = new ArrayList<>();

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Vertex[] shuffleAdj() {
            Vertex[] s = new Vertex[adj.size()];
            for (int i = 0; i < adj.size(); i++) {
                s[i] = adj.get(i);
            }

            for (int i = 0; i < adj.size(); i++) {
                int j = rand.nextInt(i + 1);
                Vertex t = s[i];
                s[i] = s[j];
                s[j] = t;
            }
            return s;
        }
    }
}