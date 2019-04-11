import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Solution {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Transmutation solver = new Transmutation();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++) {
            solver.solve(i, in, out);
        }
        out.close();
    }

    static class Transmutation {

        long[] debt;
        long[] count;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();

            Vertex[] vs = Vertex.emptyGraph(n);
            for (int i = 0; i < n; i++) {
                int r1 = in.nextInt() - 1;
                int r2 = in.nextInt() - 1;
                vs[i].adj.add(vs[r1]);
                vs[i].adj.add(vs[r2]);
            }

            count = new long[n];
            debt = new long[n];
            for (int i = 0; i < n; i++) {
                count[i] = in.nextInt();
            }

            long ans = count[0];
            count[0] = 0;

            long t1 = 0;
            long t2 = 100 * (long) 10e9;

            while (t1 < t2) {
                long mid = (t1 + t2 + 1) / 2;
                System.arraycopy(count, 0, debt, 0, n);
                if (possible(vs[0], mid)) {
                    t1 = mid;
                } else {
                    t2 = mid - 1;
                }
            }
            out.println(String.format("Case #%d: %d", testNumber, ans + t1));
        }

        private boolean possible(Vertex v, long d) {
            if (debt[v.idx] < 0) {
                return false;
            }
            debt[v.idx] -= d;
            if (debt[v.idx] >= 0) {
                return true;
            }

            for (Vertex to : v.adj) {
                if (!possible(to, -debt[v.idx])) {
                    return false;
                }
            }
            debt[v.idx] = 0;

            return true;
        }
    }

    static class Vertex {

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

    static class InputReader {

        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

