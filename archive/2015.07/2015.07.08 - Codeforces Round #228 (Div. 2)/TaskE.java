package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        PriorityQueue<Integer> qe = new PriorityQueue<>((o1, o2) -> o2 - o1);
        long sum = 0, ssum = 0;
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            for (int h = 0; h < m; h++) {
                int a = in.nextInt();
                ssum += a;
                if (m % 2 != 0 && h == m / 2) {
                    qe.add(a);
                } else {
                    if (h < m / 2) {
                        sum += a;
                    } else {
                        sum -= a;
                    }
                }
            }
        }
        int q = 1;
        while (qe.size() > 0) {
            sum += q * qe.poll();
            q *= -1;
        }
        out.print(((ssum - sum) / 2 + sum) + " " + ((ssum - sum) / 2));
    }
}
