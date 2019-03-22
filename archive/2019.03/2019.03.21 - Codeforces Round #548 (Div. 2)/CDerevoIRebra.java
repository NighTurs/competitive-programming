package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CDerevoIRebra {

    public static long binPow(long a, long n, long MOD) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            n >>= 1;
        }
        return res;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int color = in.nextInt();
            if (color == 0) {
                edges.get(from).add(to);
                edges.get(to).add(from);
            }
        }

        long MOD = (int) 1e9 + 7;
        boolean[] visited = new boolean[n];

        long overall = binPow(n, k, MOD);

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            LinkedList<Integer> queue = new LinkedList<>();
            visited[i] = true;
            queue.add(i);
            long num = 1;
            while (!queue.isEmpty()) {
                int top = queue.pop();
                for (int h = 0; h < edges.get(top).size(); h++) {
                    int to = edges.get(top).get(h);
                    if (!visited[to]) {
                        visited[to] = true;
                        queue.add(to);
                        num++;
                    }
                }
            }
            overall = (overall + MOD - binPow(num, k, MOD)) % MOD;
        }
        out.println(overall);
    }
}
