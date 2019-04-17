package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DPriravnyaiIhVse {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        HashMap<Integer, Integer> mp = new HashMap<>();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            mp.putIfAbsent(d, 0);
            mp.put(d, mp.get(d) + 1);
            a[i] = d;
        }

        int max = 0;
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                num = entry.getKey();
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];


        for (int i = 0; i < n; i++) {
            if (a[i] == num) {
                queue.add(i);
                visited[i] = true;
            }
        }

        out.println(n - queue.size());

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int j = -1; j <= 1; j++) {
                if (j == 0 || cur + j < 0 || cur + j >= n) {
                    continue;
                }
                int next = cur + j;
                if (visited[next]) {
                    continue;
                }
                queue.add(next);
                visited[next] = true;
                if (a[next] > num) {
                    out.print("2 ");
                } else {
                    out.print("1 ");
                }

                out.println(String.format("%d %d", next + 1, cur + 1));
            }
        }
    }
}
