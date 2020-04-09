package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FKatyaINesovershenstva {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<List<Integer>> d = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            d.add(new ArrayList<>());
        }
        boolean[] p = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            if (p[i]) {
                continue;
            }
            for (int h = i + i; h <= n; h += i) {
                d.get(h).add(i);
                p[h] = true;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!p[i]) {
                primes.add(i);
            }
        }

        int cap = primes.size() + 1;
        long comm = 1;

        for (int i = 2; i <= n; i++) {
            if (cap >= i) {
                out.print(comm);
                out.print(' ');
                continue;
            }
            while (cap < i) {
                comm++;
                int t1 = 0;
                int t2 = primes.size() - 1;
                while (t1 < t2) {
                    int mid = (t2 + t1 + 1) / 2;
                    if (primes.get(mid) * comm <= n) {
                        t1 = mid;
                    } else {
                        t2 = mid - 1;
                    }
                }
                cap += t1 + 1 - (d.get((int)comm).size() - 1);
            }
            out.print(comm);
            out.print(' ');
        }
    }
}
